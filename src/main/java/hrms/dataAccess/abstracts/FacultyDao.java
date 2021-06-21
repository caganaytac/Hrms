package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyDao extends JpaRepository<Faculty, Short> {
    List<Faculty> getByActive(boolean active);
    Faculty getByIdAndActive(short id, boolean active);
}