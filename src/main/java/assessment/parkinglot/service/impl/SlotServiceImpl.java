package assessment.parkinglot.service.impl;

import assessment.parkinglot.dto.RemainingSlotDto;
import assessment.parkinglot.entity.SlotType;
import assessment.parkinglot.exception.ParkingLotException;
import assessment.parkinglot.repository.SlotRepository;
import assessment.parkinglot.repository.SlotTypeRepository;
import assessment.parkinglot.service.SlotService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlotServiceImpl implements SlotService {
    private final SlotRepository slotRepository;
    private final SlotTypeRepository slotTypeRepository;
    public SlotServiceImpl(final SlotRepository slotRepository, final SlotTypeRepository slotTypeRepository) {
        this.slotRepository = slotRepository;
        this.slotTypeRepository = slotTypeRepository;
    }
    public List<RemainingSlotDto> getRemainingSlotType() {
        final List<RemainingSlotDto> result = new ArrayList<>();
        final List<String> typeAlias = new ArrayList<>();
        typeAlias.add(SlotTypeRepository.ALIAS_REGULAR);
        typeAlias.add(SlotTypeRepository.ALIAS_MOTORCYCLE);
        typeAlias.add(SlotTypeRepository.ALIAS_COMPACT);
        final List<SlotType> slotTypes = this.slotTypeRepository.findByAliasIn(typeAlias);
        for(final SlotType type: slotTypes) {
            result.add(new RemainingSlotDto(type.getAlias(), this.slotRepository.countBySlotTypeAndActiveTrue(type)));
        }
        return result;
    }

    public RemainingSlotDto getRemainingSlotType(String slotTypeId) {
        final Optional<SlotType> optional = this.slotTypeRepository.findById(Long.valueOf(slotTypeId));
        if(!optional.isPresent()) {
            throw new ParkingLotException(ParkingLotException.Type.SLOT_TYPE_NOT_FOUND);
        }
        final SlotType type = optional.get();
        final RemainingSlotDto dto = new RemainingSlotDto(type.getAlias(), this.slotRepository.countBySlotTypeAndActiveTrue(type));
        if(dto.getRemaining() == 0) {
            throw new ParkingLotException(ParkingLotException.Type.SLOT_NOT_AVAILABLE);
        }
        return dto;
    }
}
