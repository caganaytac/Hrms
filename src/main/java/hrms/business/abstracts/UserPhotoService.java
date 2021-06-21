package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.UserPhoto;

public interface UserPhotoService {
    DataResult<List<UserPhoto>> getAll();

    DataResult<UserPhoto> getById(Long id);

    DataResult<UserPhoto> getByUser(Integer id);

    Result add(UserPhoto userPhoto);

    Result update(UserPhoto userPhoto);

    Result delete(UserPhoto userPhoto);
}