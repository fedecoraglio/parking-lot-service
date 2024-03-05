package assessment.parkinglot.service.impl;

import assessment.parkinglot.converter.SlotTypeConverter;
import assessment.parkinglot.dto.SlotTypeDto;
import assessment.parkinglot.entity.SlotType;
import assessment.parkinglot.entity.VehicleType;
import assessment.parkinglot.repository.SlotTypeRepository;
import assessment.parkinglot.service.SlotTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotTypeServiceImpl implements SlotTypeService {
    private final SlotTypeRepository slotTypeRepository;
    private final SlotTypeConverter slotTypeConverter;
    public SlotTypeServiceImpl(final SlotTypeRepository slotTypeRepository, final SlotTypeConverter slotTypeConverter) {
        this.slotTypeRepository = slotTypeRepository;
        this.slotTypeConverter = slotTypeConverter;
    }

    public List<SlotTypeDto> findAllSlotTypes() {
        final List<SlotType> slotTypes = this.slotTypeRepository.findAll();
        return this.slotTypeConverter.convertSlotTypeToSlotTypeDto(slotTypes);
    }
}
