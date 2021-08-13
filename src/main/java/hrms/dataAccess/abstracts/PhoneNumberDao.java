package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.PhoneNumber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhoneNumberDao extends BaseDao<PhoneNumber, Integer>, JpaRepository<PhoneNumber, Integer> {
    @Query("From PhoneNumber where user.id = :userId and phoneNumber = :phoneNumber and active = true")
    PhoneNumber doesExist(Integer userId, String phoneNumber);

    @Query("From PhoneNumber where user.id = :id and active = true")
    PhoneNumber getByUser(Integer id);
}