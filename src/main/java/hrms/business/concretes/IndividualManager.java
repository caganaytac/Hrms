package hrms.business.concretes;

import hrms.business.abstracts.IndividualService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualDao;
import hrms.entities.concretes.Individual;
//import hrms.entities.dtos.CvDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IndividualManager implements IndividualService {
    private IndividualDao individualDao;

    @Autowired
    public IndividualManager(IndividualDao individualDao) {
        this.individualDao = individualDao;
    }

    @Override
    public DataResult<List<Individual>> getAll() {
        return new SuccessDataResult<>(this.individualDao.getByActive(true));
    }

    @Override
    public DataResult<Individual> getById(int id) {
        return new SuccessDataResult<>(this.individualDao.getByIdAndActive(id, true));
    }

    // @Override
    // public DataResult<CvDto> getCv(int id) {
    //     return new SuccessDataResult<CvDto>(this.individualDao.getCv(id));
    // }

    @Override
    public Result add(Individual individual) {
        individual.setCreateDate(LocalDateTime.now());
        individual.setActive(true);
        this.individualDao.save(individual);
        return new SuccessResult();
    }

    @Override
    public Result update(Individual individual) {
        return null;
    }

    @Override
    public Result delete(Individual individual) {
        return null;
    }
}