package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.IndividualTechnologyService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualTechnologyDao;
import hrms.entities.concretes.IndividualTechnology;

@Service
public class IndividualTechnologyManager implements IndividualTechnologyService {
    private IndividualTechnologyDao individualTechnologyDao;
    private final String INDIVIDUAL_TECHNOLOGY = "Individual technology";

    @Autowired
    public IndividualTechnologyManager(IndividualTechnologyDao individualTechnologyDao) {
        this.individualTechnologyDao = individualTechnologyDao;
    }

    @Override
    public DataResult<List<IndividualTechnology>> getAll() {
        return new SuccessDataResult<List<IndividualTechnology>>(this.individualTechnologyDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualTechnology> getById(Long id) {
        return new SuccessDataResult<IndividualTechnology>(this.individualTechnologyDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualTechnology>> getByIndividual(Integer id) {
        return new SuccessDataResult<List<IndividualTechnology>>(
                this.individualTechnologyDao.getByIndividual(id, true));
    }

    @Override
    public Result add(IndividualTechnology individualTechnology) {
        Result result = BusinessRules.run(doesExist(individualTechnology));
        if (result != null)
            return result;

        individualTechnology.setCreateDate(LocalDateTime.now());
        individualTechnology.setActive(true);

        this.individualTechnologyDao.save(individualTechnology);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualTechnology individualTechnology) {
        Result result = BusinessRules.run(doesExistById(individualTechnology.getId()), doesExist(individualTechnology));
        if (result != null)
            return result;

        IndividualTechnology newIndividualTechnology = getById(individualTechnology.getId()).getData();
        newIndividualTechnology.setTechnology(individualTechnology.getTechnology());

        this.individualTechnologyDao.save(newIndividualTechnology);
        return new SuccessResult();
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        IndividualTechnology oldIndividualTechnology = this.individualTechnologyDao.getByIdAndActive(id, true);
        oldIndividualTechnology.setActive(false);

        this.individualTechnologyDao.save(oldIndividualTechnology);
        return new SuccessResult();
    }

    private Result doesExist(IndividualTechnology individualTechnology) {
        IndividualTechnology result = this.individualTechnologyDao.doesExist(individualTechnology.getIndividual().getId(),
                individualTechnology.getTechnology().getId());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(INDIVIDUAL_TECHNOLOGY));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        IndividualTechnology result = this.individualTechnologyDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(INDIVIDUAL_TECHNOLOGY));
        return new SuccessResult();
    }
}