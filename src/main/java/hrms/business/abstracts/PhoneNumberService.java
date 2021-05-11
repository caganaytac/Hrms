package hrms.business.abstracts;

import hrms.entities.concretes.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {
    List<PhoneNumber> getAll();
}
