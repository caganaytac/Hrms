package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusDao extends JpaRepository<Status, Short> {
    List<Status> getByActive(boolean active);
    Status getByIdAndActive(short id, boolean active);
}