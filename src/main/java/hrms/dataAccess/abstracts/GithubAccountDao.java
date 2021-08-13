package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.GithubAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GithubAccountDao extends BaseDao<GithubAccount, Integer>, JpaRepository<GithubAccount, Integer> {
    @Query("From GithubAccount where user.id=:id and active=true")
    GithubAccount getByUser(Integer id);

    @Query("From GithubAccount where user.id = :userId and accountAddress = :accountAddress and active=true")
    GithubAccount doesExist(Integer userId, String accountAddress);
}