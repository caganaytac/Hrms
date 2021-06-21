package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.JobAdvert;

import java.util.List;

public interface JobAdvertService {
    DataResult<List<JobAdvert>> getAll();

    DataResult<JobAdvert> getById(Long id);

    DataResult<List<JobAdvert>> getAllByCorporate(Integer id);

    Result add(JobAdvert jobAdvert);

    Result update(JobAdvert jobAdvert);

    Result delete(JobAdvert jobAdvert);
}