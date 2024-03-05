package assessment.parkinglot.repository;

import assessment.parkinglot.entity.Vehicle;
import org.springframework.data.repository.ListCrudRepository;

public interface VehicleRepository extends ListCrudRepository<Vehicle, Long> {
    Vehicle findByVim(String vim);
}
