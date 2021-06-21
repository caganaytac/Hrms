package hrms.business.concretes;

import hrms.business.abstracts.SchoolFacultyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.SchoolFacultyDao;
import hrms.entities.concretes.SchoolFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchoolFacultyManager implements SchoolFacultyService {
    private SchoolFacultyDao schoolFacultyDao;

    @Autowired
    public SchoolFacultyManager(SchoolFacultyDao schoolFacultyDao) {
        this.schoolFacultyDao = schoolFacultyDao;
    }

    @Override
    public DataResult<List<SchoolFaculty>> getAll() {
        return new SuccessDataResult<List<SchoolFaculty>>(this.schoolFacultyDao.getByActive(true));
    }

    @Override
    public DataResult<SchoolFaculty> getById(int id) {
        return new SuccessDataResult<SchoolFaculty>(this.schoolFacultyDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(SchoolFaculty schoolFaculty) {
        schoolFaculty.setCreateDate(LocalDateTime.now());
        schoolFaculty.setActive(true);
        this.schoolFacultyDao.save(schoolFaculty);
        return new SuccessResult("School Faculty added.");
    }

    @Override
    public Result update(SchoolFaculty schoolFaculty) {
        return null;
    }

    @Override
    public Result delete(SchoolFaculty schoolFaculty) {
        return null;
    }

    @Override
    public DataResult<List<SchoolFaculty>> getBySchool(Integer id) {
        return new SuccessDataResult<List<SchoolFaculty>>(this.schoolFacultyDao.getBySchool_Id(id));
    }
}