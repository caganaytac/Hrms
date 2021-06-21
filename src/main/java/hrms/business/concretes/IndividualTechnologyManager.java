package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.IndividualTechnologyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualTechnologyDao;
import hrms.entities.concretes.IndividualTechnology;

@Service
public class IndividualTechnologyManager implements IndividualTechnologyService{
    private IndividualTechnologyDao individualTechnologyDao;

    @Autowired
    public IndividualTechnologyManager(IndividualTechnologyDao individualTechnologyDao){
        this.individualTechnologyDao = individualTechnologyDao;
    }

    @Override
    public DataResult<List<IndividualTechnology>> getAll() {
        return new SuccessDataResult<List<IndividualTechnology>>(this.individualTechnologyDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualTechnology> getById(long id) {
        return new SuccessDataResult<IndividualTechnology>(this.individualTechnologyDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualTechnology>> getAllByIndividual(int id) {
        return new SuccessDataResult<List<IndividualTechnology>>(this.individualTechnologyDao.getByIndividual(id, true));
    }

    @Override
    public Result add(IndividualTechnology individualTechnology) {
        individualTechnology.setCreateDate(LocalDateTime.now());
        individualTechnology.setActive(true);

        this.individualTechnologyDao.save(individualTechnology);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualTechnology individualTechnology) {
        return null;
    }

    @Override
    public Result delete(IndividualTechnology individualTechnology) {
        return null;
    }
    
}