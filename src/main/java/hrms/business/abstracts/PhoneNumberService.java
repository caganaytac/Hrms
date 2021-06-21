package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {
    DataResult<List<PhoneNumber>> getAll();

    DataResult<PhoneNumber> getById(int id);

    Result add(PhoneNumber phoneNumber);

    Result update(PhoneNumber phoneNumber);

    Result delete(PhoneNumber phoneNumber);
}