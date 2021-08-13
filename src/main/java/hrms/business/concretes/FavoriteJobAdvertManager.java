package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployeeService;
import hrms.business.abstracts.FavoriteJobAdvertService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.FavoriteJobAdvertDao;
import hrms.entities.concretes.Employee;
import hrms.entities.concretes.FavoriteJobAdvert;

@Service
public class FavoriteJobAdvertManager implements FavoriteJobAdvertService {
    private FavoriteJobAdvertDao favoriteJobAdvertDao;
    private EmployeeService employeeService;
    private static String FAVORITE_JOB_ADVERT = "Favorite job advert";

    @Autowired
    public FavoriteJobAdvertManager(FavoriteJobAdvertDao favoriteJobAdvertDao, EmployeeService employeeService) {
        this.favoriteJobAdvertDao = favoriteJobAdvertDao;
        this.employeeService = employeeService;
    }

    @Override
    public DataResult<List<FavoriteJobAdvert>> getAll() {
        return new SuccessDataResult<List<FavoriteJobAdvert>>(this.favoriteJobAdvertDao.getByActive(true));
    }

    @Override
    public DataResult<FavoriteJobAdvert> getById(Long id) {
        return new SuccessDataResult<FavoriteJobAdvert>(this.favoriteJobAdvertDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<FavoriteJobAdvert>> getByIndividual(Integer id) {
        return new SuccessDataResult<List<FavoriteJobAdvert>>(this.favoriteJobAdvertDao.getByIndividual(id));
    }

    @Override
    public Result add(FavoriteJobAdvert favoriteJobAdvert) {
        Result result = BusinessRules.run(employeeGuard(favoriteJobAdvert), doesExist(favoriteJobAdvert));
        if (result != null)
            return result;

        favoriteJobAdvert.setCreateDate(LocalDateTime.now());
        favoriteJobAdvert.setActive(true);
        this.favoriteJobAdvertDao.save(favoriteJobAdvert);

        return new SuccessResult(Messages.added(FAVORITE_JOB_ADVERT));
    }

    @Override
    public Result update(FavoriteJobAdvert favoriteJobAdvert) {
        Result result = BusinessRules.run(doesExistById(favoriteJobAdvert.getId()), doesExist(favoriteJobAdvert),
                employeeGuard(favoriteJobAdvert));
        if (result != null)
            return result;

        favoriteJobAdvert.setActive(true);
        this.favoriteJobAdvertDao.save(favoriteJobAdvert);

        return new SuccessResult(Messages.updated(FAVORITE_JOB_ADVERT));
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        FavoriteJobAdvert oldFavoriteJobAdvert = this.favoriteJobAdvertDao.getByIdAndActive(id, true);
        oldFavoriteJobAdvert.setActive(false);

        this.favoriteJobAdvertDao.save(oldFavoriteJobAdvert);
        return new SuccessResult(Messages.deleted(FAVORITE_JOB_ADVERT));
    }

    private Result doesExist(FavoriteJobAdvert favoriteJobAdvert) {
        Long jobAdvertId = favoriteJobAdvert.getJobAdvert().getId();
        Integer individualId = favoriteJobAdvert.getIndividual().getId();

        FavoriteJobAdvert result = this.favoriteJobAdvertDao.doesExist(jobAdvertId, individualId);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(FAVORITE_JOB_ADVERT));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        FavoriteJobAdvert result = this.favoriteJobAdvertDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(FAVORITE_JOB_ADVERT));
        return new SuccessResult();
    }

    private Result employeeGuard(FavoriteJobAdvert favoriteJobAdvert) {
        Employee result = this.employeeService.getByIndividual(favoriteJobAdvert.getIndividual().getId()).getData();
        if (result != null)
            return new ErrorResult(Messages.employeeGuardForFavoriteJobAdvert);
        return new SuccessResult();
    }
}