package assessment.parkinglot.converter;

import assessment.parkinglot.dto.SlotDto;
import assessment.parkinglot.dto.SlotTypeDto;
import assessment.parkinglot.entity.Slot;
import assessment.parkinglot.entity.SlotType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlotConverter {
    private final ModelMapper modelMapper;
    public SlotConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SlotDto convertSlotToSlotDto(final Slot slot) {
        final SlotDto slotDto = this.modelMapper.map(slot, SlotDto.class);
        slotDto.setSlotType(this.modelMapper.map(slot.getSlotType(), SlotTypeDto.class));
        return slotDto;
    }

    public List<SlotDto> convertSlotToSlotDto(final List<Slot> slots) {
        final List<SlotDto> slotDtos;
        if(slots != null && !slots.isEmpty()) {
            slotDtos = slots.stream().map(this::convertSlotToSlotDto).collect(Collectors.toList());
        } else {
            slotDtos = Collections.emptyList();
        }
        return slotDtos;
    }
}
