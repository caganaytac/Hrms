package hrms.business.concretes;

import hrms.business.abstracts.JobPositionService;
import hrms.core.utilities.results.DataResult;
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

    public JobPositionManager() {}

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }


    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<>(this.jobPositionDao.findAll());
    }

    @Override
    public DataResult<JobPosition> getById(short id) {
        return null;
    }

    @Override
    public Result add(JobPosition jobPosition) {
        jobPosition.setCreateDate(LocalDateTime.now());
        jobPosition.setActive(true);
        this.jobPositionDao.save(jobPosition);
        return new SuccessResult();
    }

    @Override
    public Result update(JobPosition jobPosition) {
        return null;
    }

    @Override
    public Result delete(JobPosition jobPosition) {
        return null;
    }
}
