package hrms.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobAdvertService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.JobAdvertDao;
import hrms.entities.concretes.JobAdvert;
import hrms.entities.dtos.JobAdvertDto;

@Service
public class JobAdvertManager implements JobAdvertService {
    private JobAdvertDao jobAdvertDao;
    private final String JOB_ADVERT = "Job advert";

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao) {
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    public DataResult<List<JobAdvert>> getAll() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActive(true));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getDetails() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getDetailsByActive(true));
    }

    @Override
    public DataResult<JobAdvert> getById(Long id) {
        return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<JobAdvertDto> getDetailById(Long id) {
        return new SuccessDataResult<JobAdvertDto>(this.jobAdvertDao.getDetailByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<JobAdvert>> getByCorporate(Integer id) {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByCorporateAndActive(id));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getDetailsByCorporate(Integer id) {
        return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getDetailsByCorporateAndActive(id, true));
    }

    @Override
    public Result add(JobAdvert jobAdvert) {
        Result result = BusinessRules.run(doesExist(jobAdvert));
        if (result != null)
            return result;

        jobAdvert.setCreateDate(LocalDateTime.now());
        jobAdvert.setActive(true);

        this.jobAdvertDao.save(jobAdvert);
        return new SuccessResult(Messages.added(JOB_ADVERT));
    }

    @Override
    public Result update(JobAdvert jobAdvert) {
        Result result = BusinessRules.run(doesExistById(jobAdvert.getId()), doesExist(jobAdvert));
        if (result != null)
            return result;

        JobAdvert oldJobAdvert = this.jobAdvertDao.getByIdAndActive(jobAdvert.getId(), true);
        jobAdvert.setCorporate(oldJobAdvert.getCorporate());
        jobAdvert.setCreateDate(oldJobAdvert.getCreateDate());
        jobAdvert.setActive(true);

        this.jobAdvertDao.save(jobAdvert);
        return new SuccessResult(Messages.updated(JOB_ADVERT));
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        JobAdvert oldJobAdvert = this.jobAdvertDao.getByIdAndActive(id, true);
        oldJobAdvert.setActive(false);

        this.jobAdvertDao.save(oldJobAdvert);
        return new SuccessResult(Messages.added(JOB_ADVERT));
    }

    private Result doesExist(JobAdvert jobAdvert) {
        Integer corporateId = jobAdvert.getCorporate().getId();
        Short jobPositionId = jobAdvert.getJobPosition().getId();
        Short cityId = jobAdvert.getCity().getId();
        Short workAreaId = jobAdvert.getWorkArea().getId();
        Short workTimeId = jobAdvert.getWorkTime().getId();
        Double minSalary = jobAdvert.getMinSalary();
        Double maxSalary = jobAdvert.getMaxSalary();
        Short openPosition = jobAdvert.getOpenPosition();
        LocalDate deadLine = jobAdvert.getDeadline();
        String description = jobAdvert.getDescription();
        JobAdvert result = this.jobAdvertDao.doesExist(corporateId, jobPositionId, cityId, workAreaId, workTimeId, minSalary,
                maxSalary, openPosition, deadLine, description);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(JOB_ADVERT));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        JobAdvert result = this.jobAdvertDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(JOB_ADVERT));
        return new SuccessResult();
    }
}