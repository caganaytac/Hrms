package hrms.dataAccess.abstracts;

import hrms.entities.concretes.IndividualEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualEducationDao extends JpaRepository<IndividualEducation, Long> {
    List<IndividualEducation> getByActive(boolean active);
    IndividualEducation getByIdAndActive(long id, boolean active);
}