package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.GithubAccount;

import java.util.List;

public interface GithubAccountService {
    DataResult<List<GithubAccount>> getAll();

    DataResult<GithubAccount> getById(int id);

    Result add(GithubAccount githubAccount);

    Result update(GithubAccount githubAccount);

    Result delete(GithubAccount githubAccount);
}