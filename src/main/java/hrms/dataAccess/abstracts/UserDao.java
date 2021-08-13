package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends BaseDao<User, Integer>, JpaRepository<User, Integer> {
    List<User> getByConfirmedAndActive(boolean confirmed, boolean active);

    User getByIdAndActiveAndConfirmed(Integer id, boolean confirmed, boolean active);

    User getByEmailAndActive(String email, boolean active);
}