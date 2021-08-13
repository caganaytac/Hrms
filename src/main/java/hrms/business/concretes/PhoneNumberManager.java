package hrms.business.concretes;

import hrms.business.abstracts.PhoneNumberService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.custom.Strings;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
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
    private final String PHONE_NUMBER = "Phone number";

    @Autowired
    public PhoneNumberManager(PhoneNumberDao phoneNumberDao) {
        this.phoneNumberDao = phoneNumberDao;
    }

    @Override
    public DataResult<List<PhoneNumber>> getAll() {
        return new SuccessDataResult<List<PhoneNumber>>(this.phoneNumberDao.getByActive(true));
    }

    @Override
    public DataResult<PhoneNumber> getById(Integer id) {
        return new SuccessDataResult<PhoneNumber>(this.phoneNumberDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<PhoneNumber> getByUser(Integer id) {
        return new SuccessDataResult<PhoneNumber>(this.phoneNumberDao.getByUser(id));
    }

    @Override
    public Result add(PhoneNumber phoneNumber) {
        phoneNumber.setPhoneNumber(Strings.getPhoneNumber(phoneNumber.getPhoneNumber()));
        Result result = BusinessRules.run(doesExist(phoneNumber));
        if (result != null)
            return result;

        phoneNumber.setCreateDate(LocalDateTime.now());
        phoneNumber.setActive(true);

        this.phoneNumberDao.save(phoneNumber);
        return new SuccessResult(Messages.added(PHONE_NUMBER));
    }

    @Override
    public Result update(PhoneNumber phoneNumber) {
        phoneNumber.setPhoneNumber(Strings.getPhoneNumber(phoneNumber.getPhoneNumber()));
        Result result = BusinessRules.run(doesExistById(phoneNumber.getId()), doesExist(phoneNumber));
        if (result != null)
            return result;

        PhoneNumber newPhoneNumber = getById(phoneNumber.getId()).getData();
        newPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());

        this.phoneNumberDao.save(phoneNumber);
        return new SuccessResult(Messages.updated(PHONE_NUMBER));
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        PhoneNumber oldPhoneNumber = this.phoneNumberDao.getByIdAndActive(id, true);
        oldPhoneNumber.setActive(false);

        this.phoneNumberDao.save(oldPhoneNumber);
        return new SuccessResult(Messages.deleted(PHONE_NUMBER));
    }

    private Result doesExist(PhoneNumber phoneNumber) {
        PhoneNumber result = this.phoneNumberDao.doesExist(phoneNumber.getUser().getId(), phoneNumber.getPhoneNumber());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(PHONE_NUMBER));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        PhoneNumber result = this.phoneNumberDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(PHONE_NUMBER));
        return new SuccessResult();
    }
}