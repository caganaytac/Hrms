package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.WorkTime;

public interface WorkTimeDao extends JpaRepository<WorkTime, Short> {
    List<WorkTime> getByActive(boolean active);

    WorkTime getByIdAndActive(short id, boolean active);
}