package hrms.dataAccess.abstracts;

import hrms.entities.concretes.IndividualJobPosition;
import hrms.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {
    List<IndividualJobPosition> getByActive(boolean active);
    IndividualJobPosition getByIdAndActive(int id, boolean active);
}
