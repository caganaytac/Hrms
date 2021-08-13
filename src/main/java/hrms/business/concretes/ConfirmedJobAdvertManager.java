package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.ConfirmedJobAdvertService;
import hrms.business.constans.Messages;
import hrms.core.business.baseService.BaseManager;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.ConfirmedJobAdvertDao;
import hrms.entities.concretes.ConfirmedJobAdvert;

@Service
public class ConfirmedJobAdvertManager extends BaseManager<ConfirmedJobAdvertDao, ConfirmedJobAdvert, Long>
        implements ConfirmedJobAdvertService {
    private ConfirmedJobAdvertDao confirmedJobAdvertDao;
    private final String VERIFIED_JOB_ADVERT = "Verified job advert";

    @Autowired
    public ConfirmedJobAdvertManager(ConfirmedJobAdvertDao confirmedJobAdvertDao) {
        super(confirmedJobAdvertDao, "Confirmed job advert");
        this.confirmedJobAdvertDao = confirmedJobAdvertDao;
    }

    @Override
    public DataResult<List<ConfirmedJobAdvert>> getAll() {
        return new SuccessDataResult<List<ConfirmedJobAdvert>>(this.confirmedJobAdvertDao.getByActive(true));
    }

    @Override
    public DataResult<ConfirmedJobAdvert> getById(Long id) {
        return new SuccessDataResult<ConfirmedJobAdvert>(this.confirmedJobAdvertDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<ConfirmedJobAdvert> getByJobAdvert(Long id) {
        return new SuccessDataResult<ConfirmedJobAdvert>(this.confirmedJobAdvertDao.getByJobAdvert(id));
    }

    @Override
    public DataResult<List<ConfirmedJobAdvert>> getByEmployee(Integer id) {
        return new SuccessDataResult<List<ConfirmedJobAdvert>>(this.confirmedJobAdvertDao.getByEmployee(id));
    }

    @Override
    public Result add(ConfirmedJobAdvert verifiedJobAdvert) {
        Result result = BusinessRules.run(doesExist(verifiedJobAdvert));
        if (result != null)
            return result;

        verifiedJobAdvert.setCreateDate(LocalDateTime.now());
        verifiedJobAdvert.setActive(true);

        this.confirmedJobAdvertDao.save(verifiedJobAdvert);
        return new SuccessResult(Messages.added(VERIFIED_JOB_ADVERT));
    }

    @Override
    public Result update(ConfirmedJobAdvert verifiedJobAdvert) {
        Result result = BusinessRules.run(doesExistById(verifiedJobAdvert.getId()), doesExist(verifiedJobAdvert));
        if (result != null)
            return result;

        verifiedJobAdvert.setCreateDate(LocalDateTime.now());
        verifiedJobAdvert.setActive(true);
        this.confirmedJobAdvertDao.save(verifiedJobAdvert);
        return new SuccessResult(Messages.updated(VERIFIED_JOB_ADVERT));
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        ConfirmedJobAdvert oldConfirmedJobAdvert = this.confirmedJobAdvertDao.getByIdAndActive(id, true);
        oldConfirmedJobAdvert.setActive(false);

        this.confirmedJobAdvertDao.save(oldConfirmedJobAdvert);
        return new SuccessResult(Messages.deleted(VERIFIED_JOB_ADVERT));
    }

    private Result doesExist(ConfirmedJobAdvert verifiedJobAdvert) {
        Long jobAdvertId = verifiedJobAdvert.getJobAdvert().getId();
        Integer employeeId = verifiedJobAdvert.getEmployee().getId();

        ConfirmedJobAdvert result = this.confirmedJobAdvertDao.isExist(jobAdvertId, employeeId);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(VERIFIED_JOB_ADVERT));
        return new SuccessResult();
    }
}