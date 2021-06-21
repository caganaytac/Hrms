package hrms.business.concretes;

import hrms.business.abstracts.FacultyService;
import hrms.core.utilities.results.DataResult;
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

    @Autowired
    public FacultyManager(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public DataResult<List<Faculty>> getAll() {
        return new SuccessDataResult<List<Faculty>>(this.facultyDao.getByActive(true));
    }

    @Override
    public DataResult<Faculty> getById(short id) {
        return new SuccessDataResult<Faculty>(this.facultyDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Faculty faculty) {
        faculty.setCreateDate(LocalDateTime.now());
        faculty.setActive(true);
        this.facultyDao.save(faculty);
        return new SuccessResult();
    }

    @Override
    public Result update(Faculty faculty) {
        return null;
    }

    @Override
    public Result delete(Faculty faculty) {
        return null;
    }
}
