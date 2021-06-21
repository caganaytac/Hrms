package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.LinkedinAccount;

import java.util.List;

public interface LinkedinAccountService {
    DataResult<List<LinkedinAccount>> getAll();

    DataResult<LinkedinAccount> getById(int id);

    Result add(LinkedinAccount linkedinAccount);

    Result update(LinkedinAccount linkedinAccount);

    Result delete(LinkedinAccount linkedinAccount);
}