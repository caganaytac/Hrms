package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.UserBiographyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.UserBiographyDao;
import hrms.entities.concretes.UserBiography;

@Service
public class UserBiographyManager implements UserBiographyService{
    private UserBiographyDao userBiographyDao;

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
        return null;
    }

    @Override
    public Result add(UserBiography userPhotoBiography) {
        userPhotoBiography.setCreateDate(LocalDateTime.now());
        userPhotoBiography.setActive(true);

        this.userBiographyDao.save(userPhotoBiography);
        return new SuccessResult();
    }

    @Override
    public Result update(UserBiography userPhotoBiography) {
        return null;
    }

    @Override
    public Result delete(UserBiography userPhotoBiography) {
        return null;
    }
}