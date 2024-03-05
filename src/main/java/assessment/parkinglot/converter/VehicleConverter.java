package assessment.parkinglot.converter;

import assessment.parkinglot.dto.SlotTypeDto;
import assessment.parkinglot.dto.VehicleDto;
import assessment.parkinglot.entity.SlotType;
import assessment.parkinglot.entity.Vehicle;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleConverter {
    private final ModelMapper modelMapper;
    public VehicleConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public VehicleDto convertVehicleToVehicleDto(final Vehicle vehicle) {
        return this.modelMapper.map(vehicle, VehicleDto.class);
    }
    public Vehicle convertVehicleDtoToVehicle(final VehicleDto vehicleDto) {
        return this.modelMapper.map(vehicleDto, Vehicle.class);
    }
}
