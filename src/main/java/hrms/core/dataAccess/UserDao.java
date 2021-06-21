package hrms.core.dataAccess;

import hrms.core.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> getByActive(boolean active);

    User getByIdAndActive(int id, boolean active);

    User getByEmailAndActive(String email, boolean active);

    List<User> getByEmailAndActiveIsFalse(String email);
}