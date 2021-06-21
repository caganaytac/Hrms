package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorporateDao extends JpaRepository<Corporate, Integer> {
    List<Corporate> getByActive(boolean active);
    Corporate getByIdAndActive(int id, boolean active);
}