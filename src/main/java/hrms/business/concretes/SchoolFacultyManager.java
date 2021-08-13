package hrms.business.concretes;

import hrms.business.abstracts.SchoolFacultyService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
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
    private final String SCHOOL_FACULTY = "School faculty";

    @Autowired
    public SchoolFacultyManager(SchoolFacultyDao schoolFacultyDao) {
        this.schoolFacultyDao = schoolFacultyDao;
    }

    @Override
    public DataResult<List<SchoolFaculty>> getAll() {
        return new SuccessDataResult<List<SchoolFaculty>>(this.schoolFacultyDao.getByActive(true));
    }

    @Override
    public DataResult<SchoolFaculty> getById(Integer id) {
        return new SuccessDataResult<SchoolFaculty>(this.schoolFacultyDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<SchoolFaculty>> getBySchool(Integer id) {
        return new SuccessDataResult<List<SchoolFaculty>>(this.schoolFacultyDao.getBySchool(id));
    }

    @Override
    public DataResult<List<SchoolFaculty>> getByFaculty(Short id) {
        return new SuccessDataResult<List<SchoolFaculty>>(this.schoolFacultyDao.getByFaculty(id));
    }

    @Override
    public Result add(SchoolFaculty schoolFaculty) {
        Result result = BusinessRules.run(doesExist(schoolFaculty));
        if (result != null)
            return result;

        schoolFaculty.setCreateDate(LocalDateTime.now());
        schoolFaculty.setActive(true);

        this.schoolFacultyDao.save(schoolFaculty);
        return new SuccessResult(Messages.added(SCHOOL_FACULTY));
    }

    @Override
    public Result update(SchoolFaculty schoolFaculty) {
        Result result = BusinessRules.run(doesExistById(schoolFaculty.getId()), doesExist(schoolFaculty));
        if (result != null)
            return result;

        SchoolFaculty newSchoolFaculty = getById(schoolFaculty.getId()).getData();
        newSchoolFaculty.setSchool(schoolFaculty.getSchool());
        newSchoolFaculty.setFaculty(schoolFaculty.getFaculty());

        this.schoolFacultyDao.save(newSchoolFaculty);
        return new SuccessResult(Messages.updated(SCHOOL_FACULTY));
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        SchoolFaculty oldSchoolFaculty = this.schoolFacultyDao.getByIdAndActive(id, true);
        oldSchoolFaculty.setActive(false);

        this.schoolFacultyDao.save(oldSchoolFaculty);
        return new SuccessResult(Messages.deleted(SCHOOL_FACULTY));
    }

    private Result doesExist(SchoolFaculty schoolFaculty) {
        SchoolFaculty result = this.schoolFacultyDao.doesExist(schoolFaculty.getSchool().getId(),
                schoolFaculty.getFaculty().getId());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(SCHOOL_FACULTY));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        SchoolFaculty result = this.schoolFacultyDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(SCHOOL_FACULTY));
        return new SuccessResult();
    }
}