package hrms.business.concretes;

import hrms.business.abstracts.PhoneNumberService;
import hrms.dataAccess.abstracts.PhoneNumberDao;
import hrms.entities.concretes.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<PhoneNumber> getAll() {
        return this.phoneNumberDao.findAll();
    }
}