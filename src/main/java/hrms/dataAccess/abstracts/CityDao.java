package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityDao extends BaseDao<City, Short>, JpaRepository<City, Short> {
    @Query("From City where name = :name and active = true")
    City getByName(String name);
}