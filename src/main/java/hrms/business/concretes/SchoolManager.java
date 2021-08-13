package hrms.business.concretes;

import hrms.business.abstracts.SchoolService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
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
    private final String SCHOOL = "School";

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<List<School>>(this.schoolDao.getByActive(true));
    }

    @Override
    public DataResult<School> getById(Integer id) {
        return new SuccessDataResult<School>(this.schoolDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(School school) {
        school.setName(school.getName().trim());
        Result result = BusinessRules.run(doesExist(school));
        if (result != null)
            return result;

        school.setCreateDate(LocalDateTime.now());
        school.setActive(true);

        this.schoolDao.save(school);
        return new SuccessResult(Messages.added(SCHOOL));
    }

    @Override
    public Result update(School school) {
        school.setName(school.getName().trim());
        Result result = BusinessRules.run(doesExistById(school.getId()), doesExist(school));
        if (result != null)
            return result;

        School newSchool = getById(school.getId()).getData();
        newSchool.setName(school.getName());

        this.schoolDao.save(newSchool);
        return new SuccessResult(Messages.updated(SCHOOL));
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        School oldSchool = this.schoolDao.getByIdAndActive(id, true);
        oldSchool.setActive(false);

        this.schoolDao.save(oldSchool);
        return new SuccessResult(Messages.deleted(SCHOOL));
    }

    private Result doesExist(School school) {
        School result = this.schoolDao.getByName(school.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(SCHOOL));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        School result = this.schoolDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(SCHOOL));
        return new SuccessResult();
    }
}