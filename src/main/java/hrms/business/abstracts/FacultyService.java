package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Faculty;

import java.util.List;

public interface FacultyService {
    DataResult<List<Faculty>> getAll();

    DataResult<Faculty> getById(short id);

    Result add(Faculty faculty);

    Result update(Faculty faculty);

    Result delete(Faculty faculty);
}