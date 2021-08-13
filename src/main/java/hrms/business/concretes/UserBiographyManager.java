package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.UserBiographyService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.UserBiographyDao;
import hrms.entities.concretes.UserBiography;

@Service
public class UserBiographyManager implements UserBiographyService {
    private UserBiographyDao userBiographyDao;
    private final String USER_BIOGRAPY = "User biography";

    @Autowired
    public UserBiographyManager(UserBiographyDao userBiographyDao) {
        this.userBiographyDao = userBiographyDao;
    }

    @Override
    public DataResult<List<UserBiography>> getAll() {
        return new SuccessDataResult<List<UserBiography>>(this.userBiographyDao.getByActive(true));
    }

    @Override
    public DataResult<UserBiography> getById(Long id) {
        return new SuccessDataResult<UserBiography>(this.userBiographyDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<UserBiography> getByUser(Integer id) {
        return new SuccessDataResult<UserBiography>(this.userBiographyDao.getByUser(id));
    }

    @Override
    public Result add(UserBiography userBiography) {
        Result result = BusinessRules.run(doesExist(userBiography));
        if (result != null)
            return result;

        userBiography.setCreateDate(LocalDateTime.now());
        userBiography.setActive(true);

        this.userBiographyDao.save(userBiography);
        return new SuccessResult();
    }

    @Override
    public Result update(UserBiography userBiography) {
        Result result = BusinessRules.run(doesExistById(userBiography.getId()), doesExist(userBiography));
        if (result != null)
            return result;

        UserBiography newUserBiography = this.userBiographyDao.getByIdAndActive(userBiography.getId(), true);
        newUserBiography.setBiography(userBiography.getBiography());

        this.userBiographyDao.save(newUserBiography);
        return new SuccessResult();
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        UserBiography oldUserBiography = this.userBiographyDao.getByIdAndActive(id, true);
        oldUserBiography.setActive(false);

        this.userBiographyDao.save(oldUserBiography);
        return new SuccessResult();
    }

    private Result doesExist(UserBiography userBiography) {
        UserBiography result = this.userBiographyDao.doesExist(userBiography.getId(), userBiography.getUser().getId(),
                userBiography.getBiography());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(USER_BIOGRAPY));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        UserBiography result = this.userBiographyDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(USER_BIOGRAPY));
        return new SuccessResult();
    }
}