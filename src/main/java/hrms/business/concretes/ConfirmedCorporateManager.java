package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.constans.Messages;
import hrms.business.abstracts.CorporateService;
import hrms.business.abstracts.CorporateToConfirmService;
import hrms.business.abstracts.ConfirmedCorporateService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.dataAccess.abstracts.ConfirmedCorporateDao;
import hrms.entities.concretes.ConfirmedCorporate;
import hrms.entities.concretes.Corporate;
import hrms.entities.concretes.CorporateToConfirm;

@Service
public class ConfirmedCorporateManager implements ConfirmedCorporateService {
    private final ConfirmedCorporateDao confirmedCorporateDao;
    private final CorporateService corporateService;
    private final CorporateToConfirmService corporateToConfirmService;
    private final String CONFIRMED_CORPORATE = "Confirmed corporate";

    @Autowired
    public ConfirmedCorporateManager(ConfirmedCorporateDao confirmedCorporateDao, CorporateService corporateService,
            CorporateToConfirmService corporateToConfirmService) {
        this.confirmedCorporateDao = confirmedCorporateDao;
        this.corporateService = corporateService;
        this.corporateToConfirmService = corporateToConfirmService;
    }

    @Override
    public DataResult<List<ConfirmedCorporate>> getAll() {
        return new SuccessDataResult<List<ConfirmedCorporate>>(this.confirmedCorporateDao.getByActive(true));
    }

    @Override
    public DataResult<ConfirmedCorporate> getById(Long id) {
        return new SuccessDataResult<ConfirmedCorporate>(this.confirmedCorporateDao.getByIdAndActive(id, true));
    }
    
    @Override
    public DataResult<List<ConfirmedCorporate>> getByCorporate(Integer id) {
        return new SuccessDataResult<List<ConfirmedCorporate>>(this.confirmedCorporateDao.getByCorporate(id));
    }

    @Override
    public DataResult<List<ConfirmedCorporate>> getByEmployee(Integer id) {
        return new SuccessDataResult<List<ConfirmedCorporate>>(this.confirmedCorporateDao.getByEmployee(id));
    }

    @Override
    public Result add(ConfirmedCorporate confirmedCorporate) {
        Corporate corporate = this.corporateService.getById(confirmedCorporate.getCorporate().getId()).getData();
        if (corporate.isConfirmed())
        return new ErrorResult(Messages.alreadyExists(CONFIRMED_CORPORATE));

        CorporateToConfirm corporateToConfirm = this.corporateToConfirmService
                .getByCorporate(confirmedCorporate.getCorporate().getId()).getData();
        corporate.setCompanyName(corporateToConfirm.getCompanyName());
        corporate.setWebsite(corporateToConfirm.getWebsite());
        this.corporateService.confirm(corporate);

        confirmedCorporate.setCreateDate(LocalDateTime.now());
        confirmedCorporate.setActive(true);

        this.confirmedCorporateDao.save(confirmedCorporate);
        return new SuccessResult(Messages.corporateConfirmed);
    }
}