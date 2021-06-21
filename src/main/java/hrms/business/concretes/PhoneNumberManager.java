package hrms.business.concretes;

import hrms.business.abstracts.PhoneNumberService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.PhoneNumberDao;
import hrms.entities.concretes.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhoneNumberManager implements PhoneNumberService {
    private PhoneNumberDao phoneNumberDao;

    public PhoneNumberManager() {}

    @Autowired
    public PhoneNumberManager(PhoneNumberDao phoneNumberDao) {
        this.phoneNumberDao = phoneNumberDao;
    }

    @Override
    public DataResult<List<PhoneNumber>> getAll() {
        return new SuccessDataResult<>(this.phoneNumberDao.getByActive(true));
    }

    @Override
    public DataResult<PhoneNumber> getById(int id) {
        return new SuccessDataResult<>(this.phoneNumberDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(PhoneNumber phoneNumber) {
        phoneNumber.setCreateDate(LocalDateTime.now());
        phoneNumber.setActive(true);

        this.phoneNumberDao.save(phoneNumber);

        return new SuccessResult();
    }

    @Override
    public Result update(PhoneNumber phoneNumber) {
        return null;
    }

    @Override
    public Result delete(PhoneNumber phoneNumber) {
        return null;
    }
}