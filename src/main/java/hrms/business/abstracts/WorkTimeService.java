package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.WorkTime;

public interface WorkTimeService {
    DataResult<List<WorkTime>> getAll();

    DataResult<WorkTime> getById(Short id);

    Result add(WorkTime workTime);

    Result update(WorkTime workTime);

    Result delete(WorkTime workTime);   
}