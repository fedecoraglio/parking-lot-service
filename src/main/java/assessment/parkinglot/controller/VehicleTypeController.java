package assessment.parkinglot.controller;

import assessment.parkinglot.dto.VehicleTypeDto;
import assessment.parkinglot.service.VehicleTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/vehicles-types")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;
    public VehicleTypeController(final VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @GetMapping()
    public ResponseEntity<List<VehicleTypeDto>> findAll() {
        return ResponseEntity.ok(this.vehicleTypeService.finAll());
    }

}
