package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Faculty;

public interface FacultyDao extends BaseDao<Faculty, Short>, JpaRepository<Faculty, Short> {
    Faculty getByNameAndActiveIsTrue(String name);
}