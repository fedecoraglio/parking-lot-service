package assessment.parkinglot.service.impl;

import assessment.parkinglot.converter.VehicleTypeConverter;
import assessment.parkinglot.dto.VehicleTypeDto;
import assessment.parkinglot.repository.VehicleTypeRepository;
import assessment.parkinglot.service.VehicleTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeConverter vehicleTypeConverter;
    public VehicleTypeServiceImpl(final VehicleTypeRepository vehicleTypeRepository,
                                  final VehicleTypeConverter vehicleTypeConverter) {
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.vehicleTypeConverter = vehicleTypeConverter;
    }

    public List<VehicleTypeDto> finAll() {
        return this.vehicleTypeConverter.convertVehicleTypeToVehicleTypeDto(
                this.vehicleTypeRepository.findAll());
    }
}
