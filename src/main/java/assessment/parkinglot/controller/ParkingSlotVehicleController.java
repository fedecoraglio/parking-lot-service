package assessment.parkinglot.controller;

import assessment.parkinglot.dto.ParkingSlotVehicleDto;
import assessment.parkinglot.dto.SlotVehicleDto;
import assessment.parkinglot.service.ParkingSlotVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/parking-slots-vehicles")
public class ParkingSlotVehicleController {

    private final ParkingSlotVehicleService parkingVehicleService;

    public ParkingSlotVehicleController(final ParkingSlotVehicleService parkingVehicleService) {
        this.parkingVehicleService = parkingVehicleService;
    }

    @PostMapping()
    public ResponseEntity<List<SlotVehicleDto>> parkingVehicle(@RequestBody final ParkingSlotVehicleDto parkingSlotVehicleDto) {
        return ResponseEntity.ok(parkingVehicleService.parkVehicle(parkingSlotVehicleDto));
    }
}
