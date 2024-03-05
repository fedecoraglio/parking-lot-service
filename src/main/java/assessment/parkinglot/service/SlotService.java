package assessment.parkinglot.service;

import assessment.parkinglot.dto.RemainingSlotDto;

import java.util.List;

public interface SlotService {
    List<RemainingSlotDto> getRemainingSlotType();
    RemainingSlotDto getRemainingSlotType(String slotTypeId);
}
