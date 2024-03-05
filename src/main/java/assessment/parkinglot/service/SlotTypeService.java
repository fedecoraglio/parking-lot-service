package assessment.parkinglot.service;

import assessment.parkinglot.dto.SlotTypeDto;

import java.util.List;

public interface SlotTypeService {
    List<SlotTypeDto> findAllSlotTypes();
}
