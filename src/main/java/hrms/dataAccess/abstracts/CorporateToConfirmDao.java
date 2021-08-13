package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.CorporateToConfirm;

public interface CorporateToConfirmDao
                extends BaseDao<CorporateToConfirm, Integer>, JpaRepository<CorporateToConfirm, Integer> {
        @Query("From CorporateToConfirm where corporate.id = :id and active = true")
        CorporateToConfirm getByCorporate(Integer id);

        @Query("From CorporateToConfirm where corporate.id = :corporateId and companyName = :companyName and website = :website and active = true")
        CorporateToConfirm doesExist(Integer corporateId, String companyName, String website);
}