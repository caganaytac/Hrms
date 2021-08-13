package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.WorkTime;

public interface WorkTimeDao extends BaseDao<WorkTime, Short>, JpaRepository<WorkTime, Short> {
    @Query("From WorkTime where name = :name and active = true")
    WorkTime getByName(String name);
}