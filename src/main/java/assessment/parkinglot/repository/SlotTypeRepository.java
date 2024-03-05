package assessment.parkinglot.repository;

import assessment.parkinglot.entity.SlotType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface SlotTypeRepository extends ListCrudRepository<SlotType, Long> {
    String ALIAS_REGULAR = "regular";
    String ALIAS_COMPACT = "compact";
    String ALIAS_MOTORCYCLE = "motorcycle";
    List<SlotType> findByAliasIn(Collection<String> alias);
}
