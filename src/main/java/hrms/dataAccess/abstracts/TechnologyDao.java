package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.Technology;

public interface TechnologyDao extends JpaRepository<Technology, Short> {
    List<Technology> getByActive(boolean active);

    Technology getByIdAndActive(short id, boolean active);
}