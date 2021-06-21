package hrms.dataAccess.abstracts;

import hrms.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityDao extends JpaRepository<City, Short> {
    List<City> getByActive(boolean active);
    City getByIdAndActive(short id, boolean active);
}