package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.IndividualTechnology;

public interface IndividualTechnologyDao
        extends BaseDao<IndividualTechnology, Long>, JpaRepository<IndividualTechnology, Long> {
    @Query("From IndividualTechnology where individual.id=:id and active=:active")
    List<IndividualTechnology> getByIndividual(int id, boolean active);

    @Query("From IndividualTechnology where individual.id = :individualId and technology.id = :technologyId and active = true")
    IndividualTechnology doesExist(Integer individualId, short technologyId);
}