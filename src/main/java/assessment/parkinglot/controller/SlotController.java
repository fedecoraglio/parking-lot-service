package assessment.parkinglot.controller;

import assessment.parkinglot.dto.RemainingSlotDto;
import assessment.parkinglot.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/slots")
public class SlotController {
    private final SlotService slotService;
    public SlotController(final SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/remaining")
    public ResponseEntity<List<RemainingSlotDto>> getRemaining() {
        return ResponseEntity.ok(this.slotService.getRemainingSlotType());
    }

    @GetMapping("/type/{slotTypeId}/remaining")
    public ResponseEntity<RemainingSlotDto> getRemainingType(@PathVariable String slotTypeId) {
        return ResponseEntity.ok(this.slotService.getRemainingSlotType(slotTypeId));
    }

}
