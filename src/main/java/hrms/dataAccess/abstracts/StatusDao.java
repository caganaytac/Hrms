package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusDao extends BaseDao<Status, Short>, JpaRepository<Status, Short> {
    @Query("From Status where name = :name and active = true")
    Status getByName(String name);
}