package hrms.business.concretes;

import hrms.business.abstracts.CorporateService;
import hrms.dataAccess.abstracts.CorporateDao;
import hrms.entities.concretes.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateManager implements CorporateService {
    private CorporateDao corporateDao;

    public CorporateManager() {}

    @Autowired
    public CorporateManager(CorporateDao corporateDao) {
        this.corporateDao = corporateDao;
    }

    @Override
    public List<Corporate> getAll() {
        return this.corporateDao.findAll();
    }
}