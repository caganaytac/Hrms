package hrms.dataAccess.abstracts;

import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualJobPositionDao extends JpaRepository<IndividualJobPosition, Integer> {
}
