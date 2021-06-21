package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {
    DataResult<List<JobPosition>> getAll();

    DataResult<JobPosition> getById(short id);

    Result add(JobPosition jobPosition);

    Result update(JobPosition jobPosition);

    Result delete(JobPosition jobPosition);
}