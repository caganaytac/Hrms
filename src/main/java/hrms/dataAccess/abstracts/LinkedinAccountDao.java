package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.LinkedinAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LinkedinAccountDao extends BaseDao<LinkedinAccount, Integer>, JpaRepository<LinkedinAccount, Integer> {
    @Query("From LinkedinAccount where user.id = :id and active = true")
    LinkedinAccount getByUser(Integer id);

    @Query("From LinkedinAccount where user.id = :userId and accountAddress = :accountAddress and active = true")
    LinkedinAccount doesExist(Integer userId, String accountAddress);
}