package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.JobPosition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobPositionDao extends BaseDao<JobPosition, Short>, JpaRepository<JobPosition, Short> {
    @Query("From JobPosition where name = :name and active = true")
    JobPosition getByName(String name);
}