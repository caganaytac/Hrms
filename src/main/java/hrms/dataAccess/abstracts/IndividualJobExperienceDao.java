package hrms.dataAccess.abstracts;

import hrms.entities.concretes.IndividualJobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualJobExperienceDao extends JpaRepository<IndividualJobExperience, Long> {
    List<IndividualJobExperience> getByActive(boolean active);
    IndividualJobExperience getByIdAndActive(long id, boolean active);
}