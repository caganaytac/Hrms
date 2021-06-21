package hrms.business.concretes;

import hrms.business.abstracts.GithubAccountService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.GithubAccountDao;
import hrms.entities.concretes.GithubAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GithubAccountManager implements GithubAccountService {
    private GithubAccountDao githubAccountDao;

    @Autowired
    public GithubAccountManager(GithubAccountDao githubAccountDao) {
        this.githubAccountDao = githubAccountDao;
    }

    @Override
    public DataResult<List<GithubAccount>> getAll() {
        return new SuccessDataResult<List<GithubAccount>>(this.githubAccountDao.getByActive(true));
    }

    @Override
    public DataResult<GithubAccount> getById(int id) {
        return new SuccessDataResult<GithubAccount>(this.githubAccountDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(GithubAccount githubAccount) {
        githubAccount.setCreateDate(LocalDateTime.now());
        githubAccount.setActive(true);
        this.githubAccountDao.save(githubAccount);
        return new SuccessResult();
    }

    @Override
    public Result update(GithubAccount githubAccount) {
        return null;
    }

    @Override
    public Result delete(GithubAccount githubAccount) {
        return null;
    }
}