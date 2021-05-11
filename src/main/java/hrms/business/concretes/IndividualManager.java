package hrms.business.concretes;

import hrms.business.abstracts.IndividualService;
import hrms.dataAccess.abstracts.IndividualDao;
import hrms.entities.concretes.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualManager implements IndividualService {
    private IndividualDao individualDao;

    public IndividualManager() {}

    @Autowired
    public IndividualManager(IndividualDao individualDao) {
        this.individualDao = individualDao;
    }

    @Override
    public List<Individual> getAll() {
        return this.individualDao.findAll();
    }
}