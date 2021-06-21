package hrms.dataAccess.abstracts;

import hrms.entities.concretes.LinkedinAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkedinAccountDao extends JpaRepository<LinkedinAccount, Integer> {
    List<LinkedinAccount> getByActive(boolean active);
    LinkedinAccount getByIdAndActive(int id, boolean active);
}