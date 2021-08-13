package hrms.business.concretes;

import hrms.business.abstracts.FacultyService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.FacultyDao;
import hrms.entities.concretes.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacultyManager implements FacultyService {
    private FacultyDao facultyDao;
    private final String FACULTY = "Faculty";

    @Autowired
    public FacultyManager(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public DataResult<List<Faculty>> getAll() {
        return new SuccessDataResult<List<Faculty>>(this.facultyDao.getByActive(true));
    }

    @Override
    public DataResult<Faculty> getById(Short id) {
        return new SuccessDataResult<Faculty>(this.facultyDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Faculty faculty) {
        faculty.setName(faculty.getName().trim());
        Result result = BusinessRules.run(doesExist(faculty));
        if (result != null)
            return result;

        faculty.setCreateDate(LocalDateTime.now());
        faculty.setActive(true);

        this.facultyDao.save(faculty);
        return new SuccessResult(Messages.added(FACULTY));
    }

    @Override
    public Result update(Faculty faculty) {
        faculty.setName(faculty.getName().trim());
        Result result = BusinessRules.run(doesExistById(faculty.getId()), doesExist(faculty));
        if (result != null)
            return result;

        Faculty oldfaculty = getById(faculty.getId()).getData();
        oldfaculty.setName(faculty.getName());

        this.facultyDao.save(oldfaculty);
        return new SuccessResult(Messages.updated(FACULTY));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Faculty oldfaculty = this.facultyDao.getByIdAndActive(id, true);
        oldfaculty.setActive(false);

        this.facultyDao.save(oldfaculty);
        return new SuccessResult(Messages.deleted(FACULTY));
    }

    private Result doesExist(Faculty faculty) {
        String name = faculty.getName();
        Faculty result = this.facultyDao.getByNameAndActiveIsTrue(name);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(FACULTY));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        Faculty result = this.facultyDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(FACULTY));
        return new SuccessResult();
    }
}