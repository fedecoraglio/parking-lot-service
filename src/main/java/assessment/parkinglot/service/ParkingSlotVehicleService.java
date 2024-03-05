package assessment.parkinglot.service;

import assessment.parkinglot.dto.ParkingSlotVehicleDto;
import assessment.parkinglot.dto.SlotVehicleDto;

import java.util.List;

public interface ParkingSlotVehicleService {
    List<SlotVehicleDto> parkVehicle(final ParkingSlotVehicleDto parkingSlotVehicleDto);
}
