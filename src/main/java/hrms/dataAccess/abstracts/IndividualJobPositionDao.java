package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.IndividualJobPosition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualJobPositionDao
        extends BaseDao<IndividualJobPosition, Long>, JpaRepository<IndividualJobPosition, Long> {
    List<IndividualJobPosition> getByIndividualIdAndActive(Integer id, boolean active);

    List<IndividualJobPosition> getByJobPositionIdAndActive(Short id, boolean active);

    @Query("From IndividualJobPosition where jobPosition.id = :jobPositionId and individual.id = :individualId and active = true")
    IndividualJobPosition doesExist(Short jobPositionId, Integer individualId);
}