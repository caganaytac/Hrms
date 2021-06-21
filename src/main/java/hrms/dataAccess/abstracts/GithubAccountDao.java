package hrms.dataAccess.abstracts;

import hrms.entities.concretes.GithubAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GithubAccountDao extends JpaRepository<GithubAccount, Integer> {
    List<GithubAccount> getByActive(boolean active);

    GithubAccount getByIdAndActive(int id, boolean active);
}