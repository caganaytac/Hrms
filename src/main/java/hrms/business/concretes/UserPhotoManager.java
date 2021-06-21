package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.UserPhotoService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.UserPhotoDao;
import hrms.entities.concretes.UserPhoto;

@Service
public class UserPhotoManager implements UserPhotoService{
    private UserPhotoDao userPhotoDao;
    
    @Autowired
    public UserPhotoManager(UserPhotoDao userPhotoDao) {
        this.userPhotoDao = userPhotoDao;
    }

    @Override
    public DataResult<List<UserPhoto>> getAll() {
        return new SuccessDataResult<List<UserPhoto>>(this.userPhotoDao.getByActive(true));
    }

    @Override
    public DataResult<UserPhoto> getById(Long id) {
        return new SuccessDataResult<UserPhoto>(this.userPhotoDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<UserPhoto> getByUser(Integer id) {
        return null;
    }

    @Override
    public Result add(UserPhoto userPhoto) {
        userPhoto.setCreateDate(LocalDateTime.now());
        userPhoto.setActive(true);

        this.userPhotoDao.save(userPhoto);
        return new SuccessResult();
    }

    @Override
    public Result update(UserPhoto userPhoto) {
        return null;
    }

    @Override
    public Result delete(UserPhoto userPhoto) {
        return null;
    }   
}