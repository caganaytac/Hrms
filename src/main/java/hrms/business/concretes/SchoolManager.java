package hrms.business.concretes;

import hrms.business.abstracts.SchoolService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.SchoolDao;
import hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchoolManager implements SchoolService {
    private SchoolDao schoolDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<List<School>>(this.schoolDao.getByActive(true));
    }

    @Override
    public DataResult<School> getById(int id) {
        return new SuccessDataResult<School>(this.schoolDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(School school) {
        school.setCreateDate(LocalDateTime.now());
        school.setActive(true);
        this.schoolDao.save(school);
        return new SuccessResult();
    }

    @Override
    public Result update(School school) {
        return null;
    }

    @Override
    public Result delete(School school) {
        return null;
    }
}
