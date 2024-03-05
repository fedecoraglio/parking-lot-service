package assessment.parkinglot.service.impl;

import assessment.parkinglot.converter.SlotConverter;
import assessment.parkinglot.converter.SlotVehicleConverter;
import assessment.parkinglot.dto.ParkingSlotVehicleDto;
import assessment.parkinglot.dto.SlotVehicleDto;
import assessment.parkinglot.entity.*;
import assessment.parkinglot.exception.ParkingLotException;
import assessment.parkinglot.repository.*;
import assessment.parkinglot.service.ParkingSlotVehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service()
public class ParkingSlotVehicleServiceImpl implements ParkingSlotVehicleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingSlotVehicleServiceImpl.class);
    private final SlotRepository slotRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final SlotTypeRepository slotTypeRepository;
    private final SlotVehicleRepository slotVehicleRepository;
    private final SlotConverter slotConverter;
    private final SlotVehicleConverter slotVehicleConverter;
    public ParkingSlotVehicleServiceImpl(final SlotRepository slotRepository, final VehicleRepository vehicleRepository,
                                         final VehicleTypeRepository vehicleTypeRepository, final SlotTypeRepository slotTypeRepository,
                                         final SlotConverter slotConverter, final SlotVehicleRepository slotVehicleRepository,
                                         final SlotVehicleConverter slotVehicleConverter) {
        this.slotRepository = slotRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.slotTypeRepository = slotTypeRepository;
        this.slotConverter = slotConverter;
        this.slotVehicleRepository = slotVehicleRepository;
        this.slotVehicleConverter = slotVehicleConverter;
    }

    @Transactional()
    public List<SlotVehicleDto> parkVehicle(final ParkingSlotVehicleDto parkingSlotVehicleDto) {
        validateParam(parkingSlotVehicleDto);
        LOGGER.debug("Entering parkVehicle method. Vim: {}. Type {}",
                parkingSlotVehicleDto.getVehicleVim(), parkingSlotVehicleDto.getVehicleTypeId());
        final VehicleType vehicleType = getVehicleType(Long.valueOf(parkingSlotVehicleDto.getVehicleTypeId()));
        final List<SlotType> slotTypeIds = this.getSlotTypeIdsByVehicleType(vehicleType);
        final List<Slot> slots = this.slotRepository.findAllBySlotTypeInAndActiveTrue(slotTypeIds);
        final int numberOfSlots = VehicleTypeRepository.ALIAS_VANS.equals(vehicleType.getAlias()) ? 3 : 1;
        if(slots.isEmpty() || numberOfSlots > slots.size()) {
            throw new ParkingLotException(ParkingLotException.Type.SLOT_NOT_AVAILABLE);
        }

        final Vehicle vehicle = this.getOrCreateVehicleByVim(parkingSlotVehicleDto.getVehicleVim(), vehicleType);
        final List<SlotVehicle> slotVehicles = this.saveSlotVehicle(slots, vehicle, numberOfSlots);

        return this.slotVehicleConverter.convertSlotVehicleToSlotVehicleDto(slotVehicles);
    }

    private void validateParam(final ParkingSlotVehicleDto parkingSlotVehicleDto) {
        if(parkingSlotVehicleDto.getVehicleTypeId() == null) {
            throw new ParkingLotException(ParkingLotException.Type.VEHICLE_TYPE_NOT_FOUND);
        } else if(parkingSlotVehicleDto.getVehicleVim() == null) {
            throw new ParkingLotException(ParkingLotException.Type.VIM_IS_REQUIRED);
        }
    }
    private List<SlotVehicle> saveSlotVehicle(final List<Slot> slots, final Vehicle vehicle, final int numberOfSlots) {
        try {
            final List<Slot> slotsTaken = new ArrayList<>(numberOfSlots);
            final List<SlotVehicle> slotsVehicleTaken = new ArrayList<>(numberOfSlots);
            for (int i = 0; i < numberOfSlots; i++) {
                final Slot currentSlot = slots.get(i);
                currentSlot.setActive(false);
                slotsTaken.add(currentSlot);

                final SlotVehicle slotVehicle = new SlotVehicle();
                slotVehicle.setVehicle(vehicle);
                slotVehicle.setSlot(currentSlot);
                slotVehicle.setReleaseTime(null);
                slotsVehicleTaken.add(slotVehicle);
            }
            slotRepository.saveAll(slotsTaken);
            return slotVehicleRepository.saveAll(slotsVehicleTaken);
        } catch (final Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
            throw new ParkingLotException(ParkingLotException.Type.SLOT_VEHICLE_ERROR_SAVING);
        }
    }

    private Vehicle getOrCreateVehicleByVim(final String vim, final VehicleType vehicleType) {
        Vehicle vehicle = vehicleRepository.findByVim(vim);
        if(vehicle == null) {
            LOGGER.debug("Creating new vehicle");
            final Vehicle newVehicle = new Vehicle();
            newVehicle.setVim(vim);
            newVehicle.setType(vehicleType);
            vehicle = vehicleRepository.save(newVehicle);
        }
        LOGGER.debug("Vehicle id {}", vehicle.getId());
        return vehicle;
    }

    private VehicleType getVehicleType(final long id) {
        final Optional<VehicleType> vehicleType = this.vehicleTypeRepository.findById(id);
        if(!vehicleType.isPresent()) {
            throw new ParkingLotException(ParkingLotException.Type.VEHICLE_TYPE_NOT_FOUND);
        }
        return vehicleType.get();
    }

    private List<SlotType> getSlotTypeIdsByVehicleType(final VehicleType vehicleType) {
        final List<String> slotTypeAlias = this.getSlotTypeAliasByVehicleType(vehicleType);
        return this.slotTypeRepository.findByAliasIn(slotTypeAlias);
                //.stream()
                //.map(SlotType::getId)
                //.toList();
    }
    private List<String> getSlotTypeAliasByVehicleType(final VehicleType vehicleType) {
        final List<String> slotAliasTypes = new ArrayList<>();
        if(VehicleTypeRepository.ALIAS_CARS.equals(vehicleType.getAlias())) {
            slotAliasTypes.add(SlotTypeRepository.ALIAS_REGULAR);
            slotAliasTypes.add(SlotTypeRepository.ALIAS_COMPACT);
        } else if(VehicleTypeRepository.ALIAS_MOTORCYCLES.equals(vehicleType.getAlias())) {
            slotAliasTypes.add(SlotTypeRepository.ALIAS_MOTORCYCLE);
        } else if(VehicleTypeRepository.ALIAS_VANS.equals(vehicleType.getAlias())) {
            slotAliasTypes.add(SlotTypeRepository.ALIAS_REGULAR);
        }
        return slotAliasTypes;
    }
}
