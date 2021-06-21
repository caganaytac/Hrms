package hrms.business.concretes;

import hrms.business.abstracts.LinkedinAccountService;
import hrms.core.utilities.results.DataResult;
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

    @Autowired
    public LinkedinAccountManager(LinkedinAccountDao linkedinAccountDao) {
        this.linkedinAccountDao = linkedinAccountDao;
    }

    @Override
    public DataResult<List<LinkedinAccount>> getAll() {
        return new SuccessDataResult<List<LinkedinAccount>>(this.linkedinAccountDao.getByActive(true));
    }

    @Override
    public DataResult<LinkedinAccount> getById(int id) {
        return new SuccessDataResult<LinkedinAccount>(this.linkedinAccountDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(LinkedinAccount linkedinAccount) {
        linkedinAccount.setCreateDate(LocalDateTime.now());
        linkedinAccount.setActive(true);
        this.linkedinAccountDao.save(linkedinAccount);
        return new SuccessResult();
    }

    @Override
    public Result update(LinkedinAccount linkedinAccount) {
        return null;
    }

    @Override
    public Result delete(LinkedinAccount linkedinAccount) {
        return null;
    }
}
