package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.PhoneNumber;

public interface PhoneNumberService extends BaseService<PhoneNumber, Integer> {
    DataResult<PhoneNumber> getByUser(Integer id);
}