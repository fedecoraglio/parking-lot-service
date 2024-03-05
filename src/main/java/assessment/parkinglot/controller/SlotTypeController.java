package assessment.parkinglot.controller;

import assessment.parkinglot.dto.SlotTypeDto;
import assessment.parkinglot.service.SlotTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/slots-types")
public class SlotTypeController {
    private final SlotTypeService slotTypeService;
    public SlotTypeController(final SlotTypeService slotTypeService) {
        this.slotTypeService = slotTypeService;
    }

    @GetMapping()
    public ResponseEntity<List<SlotTypeDto>> findAllSlotTypes() {
        return ResponseEntity.ok(this.slotTypeService.findAllSlotTypes());
    }

}
