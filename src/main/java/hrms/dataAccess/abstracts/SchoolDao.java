package hrms.dataAccess.abstracts;

import hrms.entities.concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolDao extends JpaRepository<School, Integer> {
    List<School> getByActive(boolean active);
    School getByIdAndActive(int id, boolean active);
}