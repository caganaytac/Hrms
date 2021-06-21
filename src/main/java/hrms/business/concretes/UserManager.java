package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.utilities.results.*;
import hrms.core.dataAccess.UserDao;
import hrms.core.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(this.userDao.getByActive(true));
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.getByEmailAndActive(email, true));
    }

    @Override
    public DataResult<User> getById(int id) {
        return new SuccessDataResult<User>(this.userDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(User user) {
        var result = getByEmail(user.getEmail().toLowerCase()).getData();
        if (result != null) return new ErrorResult("User already exist.");

        user.setEmail(user.getEmail().toLowerCase());
        user.setCreateDate(LocalDateTime.now());
        user.setActive(true);

        this.userDao.save(user);
        return new SuccessResult("User added.");
    }

    @Override
    public Result update(User user) {
        var oldUser = getById(user.getId()).getData();
        var newUser = getById(user.getId()).getData();

        newUser.setEmail(user.getEmail().toLowerCase());
        newUser.setCreateDate(LocalDateTime.now());
        this.userDao.save(newUser);

        oldUser.setId(0);
        oldUser.setActive(false);
        this.userDao.save(oldUser);

        return new SuccessResult("User updated.");
    }

    @Override
    public Result delete(User user) {
        var oldUser = getById(user.getId()).getData();

        oldUser.setActive(false);
        this.userDao.save(oldUser);

        return new SuccessResult("User deleted.");
    }
}