package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.School;

import java.util.List;

public interface SchoolService {
    DataResult<List<School>> getAll();

    DataResult<School> getById(int id);

    Result add(School school);

    Result update(School school);

    Result delete(School school);
}