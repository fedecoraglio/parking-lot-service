package assessment.parkinglot.converter;

import assessment.parkinglot.dto.SlotDto;
import assessment.parkinglot.dto.SlotVehicleDto;
import assessment.parkinglot.dto.VehicleDto;
import assessment.parkinglot.entity.SlotVehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlotVehicleConverter {
    private final ModelMapper modelMapper;
    public SlotVehicleConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SlotVehicleDto convertSlotVehicleToSlotVehicleDto(final SlotVehicle slotVehicle) {
        final SlotVehicleDto dto = this.modelMapper.map(slotVehicle, SlotVehicleDto.class);
        dto.setVehicleDto(this.modelMapper.map(slotVehicle.getVehicle(), VehicleDto.class));
        dto.setSlotDto(this.modelMapper.map(slotVehicle.getSlot(), SlotDto.class));
        return dto;
    }

    public List<SlotVehicleDto> convertSlotVehicleToSlotVehicleDto(final List<SlotVehicle> slotVehicles) {
        final List<SlotVehicleDto> dto;
        if(slotVehicles != null && !slotVehicles.isEmpty()) {
            dto = slotVehicles.stream().map(this::convertSlotVehicleToSlotVehicleDto).collect(Collectors.toList());
        } else {
            dto = Collections.emptyList();
        }
        return dto;
    }
}
