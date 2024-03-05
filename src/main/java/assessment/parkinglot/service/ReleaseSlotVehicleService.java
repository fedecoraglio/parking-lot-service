package assessment.parkinglot.service;

import assessment.parkinglot.dto.ReleaseSlotVehicleDto;
import assessment.parkinglot.dto.SlotVehicleDto;

import java.util.List;

public interface ReleaseSlotVehicleService {
    List<SlotVehicleDto> releaseSlotVehicle(ReleaseSlotVehicleDto dto);
}
