package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.IndividualEducation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualEducationDao extends BaseDao<IndividualEducation, Long>, JpaRepository<IndividualEducation, Long> {
    @Query("From IndividualEducation where individual.id=:id and active=true")
    List<IndividualEducation> getByIndividual(Integer id);

    @Query("From IndividualEducation where individual.id = :individualId and schoolFaculty.id = :schoolFacultyId and"
            + " status.id = :statusId and graduateYear = :graduateYear and active=true")
    IndividualEducation doesExist(Integer individualId, Integer schoolFacultyId, Short statusId, Short graduateYear);
}