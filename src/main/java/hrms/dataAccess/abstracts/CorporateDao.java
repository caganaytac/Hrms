package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CorporateDao extends BaseDao<Corporate, Integer>, JpaRepository<Corporate, Integer>  {
    @Query("From Corporate where user.id = :id and active = true")
    Corporate getByUser(Integer id);

    @Query("From Corporate where user.id = :userId and companyName = :companyName and website = :website and active = true")
    Corporate doesExist(Integer userId, String companyName, String website);
}