package assessment.parkinglot.converter;

import assessment.parkinglot.dto.VehicleTypeDto;
import assessment.parkinglot.entity.VehicleType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleTypeConverter {
    private final ModelMapper modelMapper;
    public VehicleTypeConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VehicleTypeDto convertVehicleTypeToVehicleTypeDto(final VehicleType vehicleType) {
        return this.modelMapper.map(vehicleType, VehicleTypeDto.class);
    }

    public List<VehicleTypeDto> convertVehicleTypeToVehicleTypeDto(final List<VehicleType> vehicleTypes) {
        final List<VehicleTypeDto> dto;
        if(vehicleTypes != null && !vehicleTypes.isEmpty()) {
            dto = vehicleTypes.stream().map(this::convertVehicleTypeToVehicleTypeDto).collect(Collectors.toList());
        } else {
            dto = Collections.emptyList();
        }
        return dto;
    }
}
