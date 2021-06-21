package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.IndividualJobExperienceService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualJobExperienceDao;
import hrms.entities.concretes.IndividualJobExperience;

@Service
public class IndividualJobExperienceManager implements IndividualJobExperienceService {
    private IndividualJobExperienceDao individualJobExperienceDao;

    @Autowired
    public IndividualJobExperienceManager(IndividualJobExperienceDao individualJobExperienceDao) {
        this.individualJobExperienceDao = individualJobExperienceDao;
    }

    @Override
    public DataResult<List<IndividualJobExperience>> getAll() {
        return new SuccessDataResult<List<IndividualJobExperience>>(this.individualJobExperienceDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualJobExperience> getById(long id) {
        return new SuccessDataResult<IndividualJobExperience>(
                this.individualJobExperienceDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualJobExperience>> getAllByIndividual(int id) {
        return null;
    }

    @Override
    public Result add(IndividualJobExperience individualJobExperience) {
        individualJobExperience.setCreateDate(LocalDateTime.now());
        individualJobExperience.setActive(true);
        
        this.individualJobExperienceDao.save(individualJobExperience);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualJobExperience individualJobExperience) {
        return null;
    }

    @Override
    public Result delete(IndividualJobExperience individualJobExperience) {
        return null;
    }

}