package assessment.parkinglot.controller;

import assessment.parkinglot.dto.ReleaseSlotVehicleDto;
import assessment.parkinglot.dto.SlotVehicleDto;
import assessment.parkinglot.service.ReleaseSlotVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/releasing-slots-vehicles")
public class ReleaseSlotVehicleController {

    private final ReleaseSlotVehicleService releaseSlotVehicleService;

    public ReleaseSlotVehicleController(final ReleaseSlotVehicleService releaseSlotVehicleService) {
        this.releaseSlotVehicleService = releaseSlotVehicleService;
    }

    @PostMapping()
    public ResponseEntity<List<SlotVehicleDto>> releaseSlotVehicle(
            @RequestBody final ReleaseSlotVehicleDto releaseSlotVehicleDto) {
        return ResponseEntity.ok(releaseSlotVehicleService.releaseSlotVehicle(releaseSlotVehicleDto));
    }
}
