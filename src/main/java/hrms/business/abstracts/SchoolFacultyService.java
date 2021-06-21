package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.SchoolFaculty;

import java.util.List;

public interface SchoolFacultyService {
    DataResult<List<SchoolFaculty>> getAll();

    DataResult<SchoolFaculty> getById(int id);

    DataResult<List<SchoolFaculty>> getBySchool(Integer id);

    Result add(SchoolFaculty schoolFaculty);

    Result update(SchoolFaculty schoolFaculty);

    Result delete(SchoolFaculty schoolFaculty);
}