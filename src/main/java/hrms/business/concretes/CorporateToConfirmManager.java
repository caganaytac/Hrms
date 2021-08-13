package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.core.business.baseService.BaseManager;
import hrms.business.abstracts.CorporateToConfirmService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.dataAccess.abstracts.CorporateToConfirmDao;
import hrms.entities.concretes.CorporateToConfirm;

@Service
public class CorporateToConfirmManager extends BaseManager<CorporateToConfirmDao, CorporateToConfirm, Integer>
        implements CorporateToConfirmService {
    private final CorporateToConfirmDao corporateToConfirmDao;
    private final String Corporate_To_Confirm = "Corporate to confirm";

    @Autowired
    public CorporateToConfirmManager(CorporateToConfirmDao corporateToConfirmDao) {
        super(corporateToConfirmDao, "Corporate to confirm");
        this.corporateToConfirmDao = corporateToConfirmDao;
    }

    @Override
    public DataResult<List<CorporateToConfirm>> getAll() {
        return new SuccessDataResult<List<CorporateToConfirm>>(this.corporateToConfirmDao.getByActive(true));
    }

    @Override
    public DataResult<CorporateToConfirm> getById(Integer id) {
        return new SuccessDataResult<CorporateToConfirm>(this.corporateToConfirmDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<CorporateToConfirm> getByCorporate(Integer id) {
        return new SuccessDataResult<CorporateToConfirm>(this.corporateToConfirmDao.getByCorporate(id));
    }

    @Override
    public Result add(CorporateToConfirm corporateToConfirm) {
        CorporateToConfirm entity = this.corporateToConfirmDao
                .getByCorporate(corporateToConfirm.getCorporate().getId());
        if (entity != null) {
            entity.setCompanyName(corporateToConfirm.getCompanyName());
            entity.setWebsite(corporateToConfirm.getWebsite());
            this.corporateToConfirmDao.save(entity);
            return new SuccessResult();
        }
        corporateToConfirm.setCreateDate(LocalDateTime.now());
        corporateToConfirm.setActive(true);

        this.corporateToConfirmDao.save(corporateToConfirm);
        return new SuccessResult();
    }

    @Override
    public Result update(CorporateToConfirm corporateToConfirm) {
        Result result = BusinessRules.run(doesExistById(corporateToConfirm.getId()), doesExist(corporateToConfirm));
        if (result != null)
            return result;

        CorporateToConfirm newCorporateToConfirm = this.corporateToConfirmDao
                .getByIdAndActive(corporateToConfirm.getId(), true);

        newCorporateToConfirm.setCompanyName(corporateToConfirm.getCompanyName());
        newCorporateToConfirm.setWebsite(corporateToConfirm.getWebsite());

        this.corporateToConfirmDao.save(newCorporateToConfirm);
        return new SuccessResult();
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        CorporateToConfirm oldCorporateToConfirm = this.corporateToConfirmDao.getByIdAndActive(id, true);
        oldCorporateToConfirm.setActive(false);

        this.corporateToConfirmDao.save(oldCorporateToConfirm);
        return new SuccessResult();
    }

    private Result doesExist(CorporateToConfirm corporateToConfirm) {
        CorporateToConfirm result = this.corporateToConfirmDao.doesExist(corporateToConfirm.getCorporate().getId(),
                corporateToConfirm.getCompanyName(), corporateToConfirm.getWebsite());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(Corporate_To_Confirm));
        return new SuccessResult();
    }
}