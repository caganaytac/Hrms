package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.ConfirmedJobAdvert;

public interface ConfirmedJobAdvertDao extends BaseDao<ConfirmedJobAdvert, Long>, JpaRepository<ConfirmedJobAdvert, Long> {
    @Query("From ConfirmedJobAdvert where jobAdvert.id= :id and active=true")
    ConfirmedJobAdvert getByJobAdvert(Long id);

    @Query("From ConfirmedJobAdvert where employee.id= :id and active=true")
    List<ConfirmedJobAdvert> getByEmployee(Integer id);

    @Query("From ConfirmedJobAdvert where jobAdvert.id= :jobAdvertId and" + " employee.id= :employeeId and active=true")
    ConfirmedJobAdvert isExist(Long jobAdvertId, Integer employeeId);
}