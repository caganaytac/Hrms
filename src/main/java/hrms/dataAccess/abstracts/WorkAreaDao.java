package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.WorkArea;

public interface WorkAreaDao extends JpaRepository<WorkArea, Short> {
    List<WorkArea> getByActive(boolean active);

    WorkArea getByIdAndActive(short id, boolean active);
}