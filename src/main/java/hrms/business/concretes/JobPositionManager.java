package hrms.business.concretes;

import hrms.business.abstracts.JobPositionService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.JobPositionDao;
import hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {
    private JobPositionDao jobPositionDao;
    private final String JOB_POSITION = "Job position";

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.getByActive(true));
    }

    @Override
    public DataResult<JobPosition> getById(Short id) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(JobPosition jobPosition) {
        jobPosition.setName(jobPosition.getName().trim());
        Result result = BusinessRules.run(doesExist(jobPosition));
        if (result != null)
            return result;

        jobPosition.setCreateDate(LocalDateTime.now());
        jobPosition.setActive(true);

        this.jobPositionDao.save(jobPosition);
        return new SuccessResult(Messages.added(JOB_POSITION));
    }

    @Override
    public Result update(JobPosition jobPosition) {
        jobPosition.setName(jobPosition.getName().trim());
        Result result = BusinessRules.run(doesExistById(jobPosition.getId()), doesExist(jobPosition));
        if (result != null)
            return result;

        JobPosition newJobPosition = getById(jobPosition.getId()).getData();
        newJobPosition.setName(jobPosition.getName());

        this.jobPositionDao.save(jobPosition);
        return new SuccessResult(Messages.updated(JOB_POSITION));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        JobPosition oldJobPosition = this.jobPositionDao.getByIdAndActive(id, true);
        oldJobPosition.setActive(false);

        this.jobPositionDao.save(oldJobPosition);
        return new SuccessResult(Messages.deleted(JOB_POSITION));
    }

    private Result doesExist(JobPosition jobPosition) {
        JobPosition result = this.jobPositionDao.getByName(jobPosition.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(JOB_POSITION));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        JobPosition result = this.jobPositionDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(JOB_POSITION));
        return new SuccessResult();
    }
}