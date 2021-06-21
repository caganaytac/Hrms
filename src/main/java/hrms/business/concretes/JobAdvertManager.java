package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobAdvertService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.JobAdvertDao;
import hrms.entities.concretes.JobAdvert;

@Service
public class JobAdvertManager implements JobAdvertService{
    private JobAdvertDao jobAdvertDao;
    
    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao) {
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    public DataResult<List<JobAdvert>> getAll() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActive(true));
    }

    @Override
    public DataResult<JobAdvert> getById(Long id) {
        return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<JobAdvert>> getAllByCorporate(Integer id) {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByCorporate_IdAndActive(id, true));
    }
    
    @Override
    public Result add(JobAdvert jobAdvert) {
        jobAdvert.setCreateDate(LocalDateTime.now());
        jobAdvert.setActive(true);

        this.jobAdvertDao.save(jobAdvert);
        return new SuccessResult();
    }

    @Override
    public Result update(JobAdvert jobAdvert) {
        return null;
    }

    @Override
    public Result delete(JobAdvert jobAdvert) {
        return null;
    }
}