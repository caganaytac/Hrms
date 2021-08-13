package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.School;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchoolDao extends BaseDao<School, Integer>, JpaRepository<School, Integer> {
    @Query("From School where name = :name and active = true")
    School getByName(String name);
}