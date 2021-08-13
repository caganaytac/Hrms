package hrms.dataAccess.abstracts;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Employee;

public interface EmployeeDao extends BaseDao<Employee, Integer>, JpaRepository<Employee, Integer> {
    @Query("From Employee where individual.user.id = :id and active = true")
    Employee getByUser(Integer id);

    @Query("From Employee where individual.id = :id and active = true")
    Employee getByIndividual(Integer id);

    @Query("From Employee where individual.id = :individualId and creditScore = :creditScore and"
            + " startDate = :startDate and finishDate = :finishDate and active = true")
    Employee doesExist(Integer individualId, Short creditScore, LocalDate startDate, LocalDate finishDate);
}