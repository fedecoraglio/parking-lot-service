package assessment.parkinglot.repository;

import assessment.parkinglot.entity.VehicleType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends ListCrudRepository<VehicleType, Long> {
    String ALIAS_CARS = "cars";
    String ALIAS_VANS = "vans";
    String ALIAS_MOTORCYCLES = "motorcycles";
}
