package hrms.business.concretes;

import hrms.business.abstracts.CorporateService;
import hrms.business.abstracts.CorporateToConfirmService;
import hrms.core.business.baseService.BaseManager;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.custom.Strings;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.CorporateDao;
import hrms.entities.concretes.Corporate;
import hrms.entities.concretes.CorporateToConfirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

@Service
public class CorporateManager extends BaseManager<CorporateDao, Corporate, Integer> implements CorporateService {
    private final CorporateDao corporateDao;
    private final CorporateToConfirmService corporateToConfirmService;
    private final String CORPORATE = "Corporate";

    @Autowired
    public CorporateManager(CorporateDao corporateDao, CorporateToConfirmService corporateToConfirmService) {
        super(corporateDao, "Corporate");
        this.corporateDao = corporateDao;
        this.corporateToConfirmService = corporateToConfirmService;
    }

    @Override
    public DataResult<List<Corporate>> getAll() {
        return new SuccessDataResult<List<Corporate>>(this.corporateDao.getByActive(true));
    }

    @Override
    public DataResult<Corporate> getById(Integer id) {
        return new SuccessDataResult<Corporate>(this.corporateDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<Corporate> getByUser(Integer id) {
        return new SuccessDataResult<Corporate>(this.corporateDao.getByUser(id));
    }

    @Override
    public Result confirm(Corporate corporate) {
        Result result = BusinessRules.run(doesExistById(corporate.getId()));
        if (result != null)
            return result;
        Corporate newCorporate = getById(corporate.getId()).getData();
        if (newCorporate.isConfirmed()) {
            return new ErrorResult(Messages.corporateAlreadyConfirmed);
        }
        newCorporate.setCompanyName(corporate.getCompanyName());
        newCorporate.setWebsite(corporate.getWebsite());
        newCorporate.setConfirmed(true);

        this.corporateDao.save(newCorporate);
        return new SuccessResult();
    }

    @Override
    public Result add(@Valid Corporate corporate) {
        corporate.setCompanyName(corporate.getCompanyName().trim());
        corporate.setWebsite(corporate.getWebsite().replaceAll("\\s", "").toLowerCase());
        Result result = BusinessRules.run(doesExist(corporate), isBusinessEmail(corporate));
        if (result != null)
            return result;

        corporate.setCreateDate(LocalDateTime.now());
        corporate.setConfirmed(false);
        corporate.setActive(true);

        this.corporateDao.save(corporate);
        return new SuccessResult(Messages.added(CORPORATE));
    }

    @Override
    public Result update(@Valid Corporate corporate) {
        corporate.setCompanyName(corporate.getCompanyName().trim());
        corporate.setWebsite(corporate.getWebsite().replaceAll("\\s", "").toLowerCase());
        Result result = BusinessRules.run(doesExistById(corporate.getId()), doesExist(corporate),
                isBusinessEmail(corporate));
        if (result != null)
            return result;

        CorporateToConfirm corporateToConfirm = new CorporateToConfirm();
        corporateToConfirm.setCorporate(corporate);
        corporateToConfirm.setCompanyName(corporate.getCompanyName());
        corporateToConfirm.setWebsite(corporate.getWebsite());
        Result result2 = this.corporateToConfirmService.add(corporateToConfirm);
        if (!result2.isSuccess())
            return new ErrorResult(result2.getMessage());

        Corporate oldCorporate = getById(corporate.getId()).getData();
        oldCorporate.setConfirmed(false);

        this.corporateDao.save(oldCorporate);
        return new SuccessResult(Messages.updated(CORPORATE));
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Corporate oldCorporate = getById(id).getData();
        oldCorporate.setActive(false);

        this.corporateDao.save(oldCorporate);
        return new SuccessResult(Messages.deleted(CORPORATE));
    }

    private Result doesExist(Corporate corporate) {
        Corporate result = this.corporateDao.doesExist(corporate.getUser().getId(), corporate.getCompanyName(),
                corporate.getWebsite());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(CORPORATE));
        return new SuccessResult();
    }

    private Result isBusinessEmail(Corporate corporate) {
        String emailDomain = corporate.getUser().getEmail().split("@")[1];
        String websiteDomain = Strings.getDomain(corporate.getWebsite());
        if (!emailDomain.equals(websiteDomain))
            return new ErrorResult(Messages.notBusinessEmail);
        return new SuccessResult();
    }
}