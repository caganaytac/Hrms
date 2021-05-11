package hrms.business.concretes;

import hrms.business.abstracts.IndividualJobPositionService;
import hrms.dataAccess.abstracts.IndividualJobPositionDao;
import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualJobPositionManager implements IndividualJobPositionService {
    private IndividualJobPositionDao individualJobPositionDao;

    public IndividualJobPositionManager() {}

    @Autowired
    public IndividualJobPositionManager(IndividualJobPositionDao individualJobPositionDao) {
        this.individualJobPositionDao = individualJobPositionDao;
    }

    @Override
    public List<IndividualJobPosition> getAll() {
        return this.individualJobPositionDao.findAll();
    }
}
