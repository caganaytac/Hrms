package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.SchoolFaculty;

import java.util.List;

public interface SchoolFacultyService extends BaseService<SchoolFaculty, Integer> {
    DataResult<List<SchoolFaculty>> getBySchool(Integer id);

    DataResult<List<SchoolFaculty>> getByFaculty(Short id);
}