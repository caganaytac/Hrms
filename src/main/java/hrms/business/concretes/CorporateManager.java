package hrms.business.concretes;

import hrms.business.abstracts.CorporateService;
import hrms.business.abstracts.UserService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.CorporateDao;
import hrms.entities.concretes.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CorporateManager implements CorporateService {
    private CorporateDao corporateDao;
    private UserService userService;

    @Autowired
    public CorporateManager(CorporateDao corporateDao, UserService userService) {
        this.corporateDao = corporateDao;
        this.userService = userService;
    }

    @Override
    public DataResult<List<Corporate>> getAll() {
        return new SuccessDataResult<List<Corporate>>(this.corporateDao.getByActive(true));
    }

    @Override
    public DataResult<Corporate> getById(int id) {
        return new SuccessDataResult<Corporate>(this.corporateDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<Corporate>> getAllByUser(int id) {
        return null;
    }

    @Override
    public DataResult<List<Corporate>> getAllByCompanyName(String companyName) {
        return null;
    }

    @Override
    public Result add(Corporate corporate) {
        var result = checkEmail(corporate);
        if (!result.isSuccess()) return result;

        corporate.setWebsite(corporate.getWebsite().toLowerCase());
        corporate.setCreateDate(LocalDateTime.now());
        corporate.setActive(true);

        this.corporateDao.save(corporate);
        return new SuccessResult("Corporate added");
    }

    @Override
    public Result update(Corporate corporate) {
        return null;
    }

    @Override
    public Result delete(Corporate corporate) {
        return null;
    }

    private Result checkEmail(Corporate corporate) {
        var email = this.userService.getById(corporate.getUser().getId()).getData().getEmail();
        for (int i = 0; i < email.length(); i++) {
            if (i == '@') {
              // if (corporate.getWebsite() == email.indexOf(email.length(), i)) { }
            }
        }
        return new SuccessResult();
    }
}