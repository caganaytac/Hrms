package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.ConfirmedCorporate;

public interface ConfirmedCorporateDao
                extends BaseDao<ConfirmedCorporate, Long>, JpaRepository<ConfirmedCorporate, Long> {
        @Query("From ConfirmedCorporate where corporate.id = :corporateId and employee.id = :employeeId and active = true")
        ConfirmedCorporate doesExist(Integer corporateId, Integer employeeId);

        @Query("From ConfirmedCorporate where employee.id = :id and active = true")
        List<ConfirmedCorporate> getByEmployee(Integer id);

        @Query("From ConfirmedCorporate where corporate.id = :id and active = true")
        List<ConfirmedCorporate> getByCorporate(Integer id);
}