package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateDao extends JpaRepository<Corporate, Integer> {
}
