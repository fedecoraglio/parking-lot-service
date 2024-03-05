package assessment.parkinglot.converter;

import assessment.parkinglot.dto.SlotTypeDto;
import assessment.parkinglot.entity.SlotType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlotTypeConverter {

    private final ModelMapper modelMapper;
    public SlotTypeConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SlotTypeDto convertSlotTypeToSlotTypeDto(final SlotType slotType) {
        return this.modelMapper.map(slotType, SlotTypeDto.class);
    }

    public List<SlotTypeDto> convertSlotTypeToSlotTypeDto(final List<SlotType> slotTypes) {
        final List<SlotTypeDto> slotTypeDtos;
        if(slotTypes != null && !slotTypes.isEmpty()) {
            slotTypeDtos = slotTypes.stream().map(this::convertSlotTypeToSlotTypeDto).collect(Collectors.toList());
        } else {
            slotTypeDtos = Collections.emptyList();
        }
        return slotTypeDtos;
    }
}
