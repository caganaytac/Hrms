package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Technology;

public interface TechnologyDao extends BaseDao<Technology, Short>, JpaRepository<Technology, Short> {
    @Query("From Technology where name = :name and active = true")
    Technology getByName(String name);
}