package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.UserBiography;

import java.util.List;

public interface UserBiographyService {
    DataResult<List<UserBiography>> getAll();

    DataResult<UserBiography> getById(Long id);

    DataResult<UserBiography> getByUser(Integer id);

    Result add(UserBiography userPhotoBiography);

    Result update(UserBiography userPhotoBiography);

    Result delete(UserBiography userPhotoBiography);   
}