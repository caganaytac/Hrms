package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hrms.business.abstracts.UserPhotoService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.core.utilities.uploaders.image.ImageService;
import hrms.dataAccess.abstracts.UserPhotoDao;
import hrms.entities.concretes.UserPhoto;

@Service
public class UserPhotoManager implements UserPhotoService {
    private UserPhotoDao userPhotoDao;
    private ImageService imageService;
    private final String USER_PHOTO = "User photo";

    @Autowired
    public UserPhotoManager(UserPhotoDao userPhotoDao, ImageService imageService) {
        this.userPhotoDao = userPhotoDao;
        this.imageService = imageService;
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
        return new SuccessDataResult<UserPhoto>(this.userPhotoDao.getByUser(id));
    }

    @Override
    public Result add(UserPhoto userPhoto, MultipartFile multipartFile) {
        DataResult<Map<String, String>> imageToUpload = this.imageService.upload(multipartFile);
        if (!imageToUpload.isSuccess())
            return new ErrorResult("Upload failed.");

        Map<String, String> photo = imageToUpload.getData();
        userPhoto.setPublicId(photo.get("public_id"));
        userPhoto.setUrl(photo.get("url"));
        userPhoto.setUser(userPhoto.getUser());

        Result result = BusinessRules.run(doesExist(userPhoto));
        if (result != null)
            return result;

        userPhoto.setCreateDate(LocalDateTime.now());
        userPhoto.setActive(true);

        this.userPhotoDao.save(userPhoto);
        return new SuccessResult(Messages.added(USER_PHOTO));
    }

    @Override
    public Result update(UserPhoto userPhoto, MultipartFile multipartFile) {
        Result result = BusinessRules.run(doesExistById(userPhoto));
        if (result != null)
            return result;

        UserPhoto newUserPhoto = getById(userPhoto.getId()).getData();
        Map<String, String> photo = this.imageService.upload(multipartFile).getData();

        newUserPhoto.setPublicId(photo.get("public_id"));
        newUserPhoto.setUrl(photo.get("url"));

        Result result2 = BusinessRules.run(doesExist(newUserPhoto));
        if (result2 != null)
            return result2;

        this.userPhotoDao.save(newUserPhoto);
        return new SuccessResult(Messages.updated(USER_PHOTO));
    }

    @Override
    public Result delete(UserPhoto userPhoto) {
        Result result = BusinessRules.run(doesExistById(userPhoto));
        if (result != null)
            return result;

        UserPhoto oldUserPhoto = getById(userPhoto.getId()).getData();
        oldUserPhoto.setActive(false);

        this.userPhotoDao.save(oldUserPhoto);
        return new SuccessResult(Messages.deleted(USER_PHOTO));
    }

    private Result doesExist(UserPhoto userPhoto) {
        UserPhoto result = this.userPhotoDao.doesExist(userPhoto.getUser().getId(), userPhoto.getPublicId(),
                userPhoto.getUrl());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(USER_PHOTO));
        return new SuccessResult();
    }

    private Result doesExistById(UserPhoto userPhoto) {
        UserPhoto result = getById(userPhoto.getId()).getData();
        if (result == null)
            return new ErrorResult(Messages.notFound(USER_PHOTO));
        return new SuccessResult();
    }
}