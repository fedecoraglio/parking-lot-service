package assessment.parkinglot.repository;

import assessment.parkinglot.entity.SlotVehicle;
import assessment.parkinglot.entity.Vehicle;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SlotVehicleRepository extends ListCrudRepository<SlotVehicle, Long> {
    List<SlotVehicle> findAllByVehicleAndReleaseTimeIsNull(Vehicle vehicle);
}
