package assessment.parkinglot.service.impl;

import assessment.parkinglot.converter.SlotVehicleConverter;
import assessment.parkinglot.dto.ReleaseSlotVehicleDto;
import assessment.parkinglot.dto.SlotVehicleDto;
import assessment.parkinglot.entity.Slot;
import assessment.parkinglot.entity.SlotVehicle;
import assessment.parkinglot.entity.Vehicle;
import assessment.parkinglot.exception.ParkingLotException;
import assessment.parkinglot.repository.SlotRepository;
import assessment.parkinglot.repository.SlotVehicleRepository;
import assessment.parkinglot.repository.VehicleRepository;
import assessment.parkinglot.service.ReleaseSlotVehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReleaseSlotVehicleServiceImpl implements ReleaseSlotVehicleService {
    private final SlotVehicleRepository slotVehicleRepository;
    private final VehicleRepository vehicleRepository;
    private final SlotRepository slotRepository;
    private final SlotVehicleConverter slotVehicleConverter;
    public ReleaseSlotVehicleServiceImpl(final SlotVehicleRepository slotVehicleRepository,
                                         final VehicleRepository vehicleRepository, final SlotRepository slotRepository,
                                         final SlotVehicleConverter slotVehicleConverter
                                         ) {
        this.slotVehicleRepository = slotVehicleRepository;
        this.vehicleRepository = vehicleRepository;
        this.slotRepository = slotRepository;
        this.slotVehicleConverter = slotVehicleConverter;
    }
    @Transactional
    public List<SlotVehicleDto> releaseSlotVehicle(final ReleaseSlotVehicleDto dto) {
        final Vehicle vehicle = vehicleRepository.findByVim(dto.getVehicleVim());
        if(vehicle == null) {
            throw new ParkingLotException(ParkingLotException.Type.VEHICLE_NOT_FOUND);
        }
        final List<SlotVehicle> currentSlotVehicles = this.slotVehicleRepository.findAllByVehicleAndReleaseTimeIsNull(vehicle);
        if(currentSlotVehicles == null || currentSlotVehicles.isEmpty()) {
            throw new ParkingLotException(ParkingLotException.Type.VEHICLE_WAS_ALREADY_RELEASED);
        }
        final List<Slot> slotsToUpdate = new ArrayList<>();
        for(final SlotVehicle slotVehicle: currentSlotVehicles) {
            final Optional<Slot> optional = this.slotRepository.findById(slotVehicle.getSlot().getId());
            if(optional.isPresent()) {
                slotVehicle.setReleaseTime(new Date());
                final Slot slot = optional.get();
                slot.setActive(true);
                slotsToUpdate.add(slot);
            }
        }
        this.slotRepository.saveAll(slotsToUpdate);
        final List<SlotVehicle> slotVehicles = this.slotVehicleRepository.saveAll(currentSlotVehicles);
        return this.slotVehicleConverter.convertSlotVehicleToSlotVehicleDto(slotVehicles);
    }
}
