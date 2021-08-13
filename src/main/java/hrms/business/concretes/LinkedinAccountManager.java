package hrms.business.concretes;

import hrms.business.abstracts.LinkedinAccountService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.LinkedinAccountDao;
import hrms.entities.concretes.LinkedinAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LinkedinAccountManager implements LinkedinAccountService {
    private LinkedinAccountDao linkedinAccountDao;
    private final String LINKEDIN_ACCOUNT = "Linkedin account";

    @Autowired
    public LinkedinAccountManager(LinkedinAccountDao linkedinAccountDao) {
        this.linkedinAccountDao = linkedinAccountDao;
    }

    @Override
    public DataResult<List<LinkedinAccount>> getAll() {
        return new SuccessDataResult<List<LinkedinAccount>>(this.linkedinAccountDao.getByActive(true));
    }

    @Override
    public DataResult<LinkedinAccount> getById(Integer id) {
        return new SuccessDataResult<LinkedinAccount>(this.linkedinAccountDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<LinkedinAccount> getByUser(Integer id) {
        return new SuccessDataResult<LinkedinAccount>(this.linkedinAccountDao.getByUser(id));
    }

    @Override
    public Result add(LinkedinAccount linkedinAccount) {
        linkedinAccount.setAccountAddress(linkedinAccount.getAccountAddress().replaceAll("\\s", ""));
        Result result = BusinessRules.run(doesExist(linkedinAccount));
        if (result != null)
            return result;

        linkedinAccount.setCreateDate(LocalDateTime.now());
        linkedinAccount.setActive(true);

        this.linkedinAccountDao.save(linkedinAccount);
        return new SuccessResult(Messages.added(LINKEDIN_ACCOUNT));
    }

    @Override
    public Result update(LinkedinAccount linkedinAccount) {
        linkedinAccount.setAccountAddress(linkedinAccount.getAccountAddress().replaceAll("\\s", ""));
        Result result = BusinessRules.run(doesExistById(linkedinAccount.getId()), doesExist(linkedinAccount));
        if (result != null)
            return result;

        LinkedinAccount newLinkedinAccount = this.linkedinAccountDao.getByIdAndActive(linkedinAccount.getId(), true);
        newLinkedinAccount.setAccountAddress(linkedinAccount.getAccountAddress());

        this.linkedinAccountDao.save(linkedinAccount);
        return new SuccessResult(Messages.updated(LINKEDIN_ACCOUNT));
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        LinkedinAccount oldLinkedinAccount = this.linkedinAccountDao.getByIdAndActive(id, true);
        oldLinkedinAccount.setActive(false);

        this.linkedinAccountDao.save(oldLinkedinAccount);
        return new SuccessResult(Messages.deleted(LINKEDIN_ACCOUNT));
    }

    private Result doesExist(LinkedinAccount linkedinAccount) {
        LinkedinAccount result = this.linkedinAccountDao.doesExist(linkedinAccount.getUser().getId(),
                linkedinAccount.getAccountAddress());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(LINKEDIN_ACCOUNT));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        LinkedinAccount result = this.linkedinAccountDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(LINKEDIN_ACCOUNT));
        return new SuccessResult();
    }
}