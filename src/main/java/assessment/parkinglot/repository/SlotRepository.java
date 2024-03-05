package assessment.parkinglot.repository;

import assessment.parkinglot.entity.Slot;
import assessment.parkinglot.entity.SlotType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlotRepository extends ListCrudRepository<Slot, Long> {

    List<Slot> findAllBySlotTypeInAndActiveTrue(List<SlotType> slotTypeIds);
    Long countBySlotTypeAndActiveTrue(SlotType slotType);
}
