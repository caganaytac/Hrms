package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.UserBiography;

public interface UserBiographyService extends BaseService<UserBiography, Long> {
    DataResult<UserBiography> getByUser(Integer id); 
}