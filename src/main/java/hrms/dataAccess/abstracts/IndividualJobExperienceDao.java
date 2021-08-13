package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.IndividualJobExperience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualJobExperienceDao extends BaseDao<IndividualJobExperience, Long>, JpaRepository<IndividualJobExperience, Long> {
    @Query("From IndividualJobExperience where individual.id=:id and active=true")
    List<IndividualJobExperience> getByIndividual(Integer id);

    @Query("From IndividualJobExperience where individual.id = :individualId and"
            + " jobPosition.id = :jobPositionId and companyName = :companyName and"
            + " startDate = :startDate and finishDate = :finishDate and active = true")
    IndividualJobExperience doesExist(Integer individualId, Short jobPositionId, String companyName, Short startDate,
            Short finishDate);
}