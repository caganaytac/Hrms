package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.User;

public interface UserService extends BaseService<User, Integer> {
    DataResult<User> getByEmail(String email);

    Result confirm(Integer id);
}