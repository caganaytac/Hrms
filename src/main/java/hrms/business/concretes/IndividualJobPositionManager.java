package hrms.business.concretes;

import hrms.business.abstracts.IndividualJobPositionService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualJobPositionDao;
import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IndividualJobPositionManager implements IndividualJobPositionService {
    private IndividualJobPositionDao individualJobPositionDao;
    private final String INDIVIDUAL_JOB_POSITION = "Individual job position";

    @Autowired
    public IndividualJobPositionManager(IndividualJobPositionDao individualJobPositionDao) {
        this.individualJobPositionDao = individualJobPositionDao;
    }

    @Override
    public DataResult<List<IndividualJobPosition>> getAll() {
        return new SuccessDataResult<List<IndividualJobPosition>>(this.individualJobPositionDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualJobPosition> getById(Long id) {
        return new SuccessDataResult<IndividualJobPosition>(this.individualJobPositionDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualJobPosition>> getByIndividual(Integer id) {
        return new SuccessDataResult<List<IndividualJobPosition>>(
                this.individualJobPositionDao.getByIndividualIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualJobPosition>> getByJobPosition(Short id) {
        return new SuccessDataResult<List<IndividualJobPosition>>(
                this.individualJobPositionDao.getByJobPositionIdAndActive(id, true));
    }

    @Override
    public Result add(IndividualJobPosition individualJobPosition) {
        Result result = BusinessRules.run(doesExist(individualJobPosition));
        if (result != null)
            return result;

        individualJobPosition.setCreateDate(LocalDateTime.now());
        individualJobPosition.setActive(true);

        this.individualJobPositionDao.save(individualJobPosition);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualJobPosition individualJobPosition) {
        Result result = BusinessRules.run(doesExistById(individualJobPosition.getId()),
                doesExist(individualJobPosition));
        if (result != null)
            return result;

        IndividualJobPosition newIndividualJobPosition = getById(individualJobPosition.getId()).getData();
        newIndividualJobPosition.setJobPosition(individualJobPosition.getJobPosition());

        this.individualJobPositionDao.save(newIndividualJobPosition);
        return new SuccessResult();
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        IndividualJobPosition oldIndividualJobPosition = this.individualJobPositionDao.getByIdAndActive(id, true);
        oldIndividualJobPosition.setActive(false);

        this.individualJobPositionDao.save(oldIndividualJobPosition);
        return new SuccessResult();
    }

    private Result doesExist(IndividualJobPosition individualJobPosition) {
        IndividualJobPosition result = this.individualJobPositionDao.doesExist(
                individualJobPosition.getJobPosition().getId(), individualJobPosition.getIndividual().getId());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(INDIVIDUAL_JOB_POSITION));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        IndividualJobPosition result = this.individualJobPositionDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(INDIVIDUAL_JOB_POSITION));
        return new SuccessResult();
    }
}