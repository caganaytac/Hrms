package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.GithubAccount;

public interface GithubAccountService extends BaseService<GithubAccount, Integer> {
    DataResult<GithubAccount> getByUser(Integer id);
}