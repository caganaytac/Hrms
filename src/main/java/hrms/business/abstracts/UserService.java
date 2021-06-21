package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.entities.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();

    DataResult<User> getByEmail(String email);

    DataResult<User> getById(int id);

    Result add(User user);

    Result update(User user);

    Result delete(User user);
}