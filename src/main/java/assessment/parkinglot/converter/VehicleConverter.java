package assessment.parkinglot.converter;

import assessment.parkinglot.dto.VehicleDto;
import assessment.parkinglot.entity.Vehicle;
import org.modelmapper.ModelMapper;

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
