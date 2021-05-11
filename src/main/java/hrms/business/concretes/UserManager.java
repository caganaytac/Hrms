package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    public UserManager() {}

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return this.userDao.findAll();
    }
}