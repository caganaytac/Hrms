package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.WorkArea;

public interface WorkAreaDao extends BaseDao<WorkArea, Short>, JpaRepository<WorkArea, Short> {
    @Query("From WorkArea where name = :name and active = true")
    WorkArea getByName(String name);
}