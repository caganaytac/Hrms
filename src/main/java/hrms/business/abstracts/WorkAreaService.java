package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.WorkArea;

public interface WorkAreaService {
    DataResult<List<WorkArea>> getAll();

    DataResult<WorkArea> getById(Short id);

    Result add(WorkArea workArea);

    Result update(WorkArea workArea);

    Result delete(WorkArea workArea);
}