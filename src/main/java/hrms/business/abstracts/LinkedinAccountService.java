package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.LinkedinAccount;

public interface LinkedinAccountService extends BaseService<LinkedinAccount, Integer> {
    DataResult<LinkedinAccount> getByUser(Integer id);
}