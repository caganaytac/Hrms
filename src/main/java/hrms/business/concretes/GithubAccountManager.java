package hrms.business.concretes;

import hrms.business.abstracts.GithubAccountService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
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
    private final String GITHUB_ACCOUNT = "Github account";

    @Autowired
    public GithubAccountManager(GithubAccountDao githubAccountDao) {
        this.githubAccountDao = githubAccountDao;
    }

    @Override
    public DataResult<List<GithubAccount>> getAll() {
        return new SuccessDataResult<List<GithubAccount>>(this.githubAccountDao.getByActive(true));
    }

    @Override
    public DataResult<GithubAccount> getById(Integer id) {
        return new SuccessDataResult<GithubAccount>(this.githubAccountDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<GithubAccount> getByUser(Integer id) {
        return new SuccessDataResult<GithubAccount>(this.githubAccountDao.getByUser(id));
    }

    @Override
    public Result add(GithubAccount githubAccount) {
        githubAccount.setAccountAddress(githubAccount.getAccountAddress().replaceAll("\\s", ""));
        Result result = BusinessRules.run(doesExist(githubAccount));
        if (result != null)
            return result;

        githubAccount.setCreateDate(LocalDateTime.now());
        githubAccount.setActive(true);

        this.githubAccountDao.save(githubAccount);
        return new SuccessResult();
    }

    @Override
    public Result update(GithubAccount githubAccount) {
        githubAccount.setAccountAddress(githubAccount.getAccountAddress().replaceAll("\\s", ""));
        Result result = BusinessRules.run(doesExistById(githubAccount.getId()), doesExist(githubAccount));
        if (result != null)
            return result;

        GithubAccount newGithubAccount = getById(githubAccount.getId()).getData();
        newGithubAccount.setAccountAddress(githubAccount.getAccountAddress());

        this.githubAccountDao.save(githubAccount);
        return new SuccessResult();
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        GithubAccount oldGithubAccount = this.githubAccountDao.getByIdAndActive(id, true);
        oldGithubAccount.setActive(false);

        this.githubAccountDao.save(oldGithubAccount);
        return new SuccessResult();
    }

    private Result doesExist(GithubAccount githubAccount) {
        GithubAccount result = this.githubAccountDao.doesExist(githubAccount.getUser().getId(),
                githubAccount.getAccountAddress());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(GITHUB_ACCOUNT));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        GithubAccount result = this.githubAccountDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(GITHUB_ACCOUNT));
        return new SuccessResult();
    }
}