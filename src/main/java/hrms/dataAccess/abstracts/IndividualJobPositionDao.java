package hrms.dataAccess.abstracts;

import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualJobPositionDao extends JpaRepository<IndividualJobPosition, Long> {
    List<IndividualJobPosition> getByActive(boolean active);
    IndividualJobPosition getByIdAndActive(long id, boolean active);
    List<IndividualJobPosition> getByIndividualIdAndActive(int id, boolean active);
    List<IndividualJobPosition> getByJobPositionIdAndActive(int id, boolean active);
}