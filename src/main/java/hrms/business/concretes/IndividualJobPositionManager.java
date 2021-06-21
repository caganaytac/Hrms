package hrms.business.concretes;

import hrms.business.abstracts.IndividualJobPositionService;
import hrms.core.utilities.results.DataResult;
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

    public IndividualJobPositionManager() {
    }

    @Autowired
    public IndividualJobPositionManager(IndividualJobPositionDao individualJobPositionDao) {
        this.individualJobPositionDao = individualJobPositionDao;
    }

    @Override
    public DataResult<List<IndividualJobPosition>> getAll() {
        return new SuccessDataResult<>(this.individualJobPositionDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualJobPosition> getById(long id) {
        return new SuccessDataResult<>(this.individualJobPositionDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualJobPosition>> getAllByIndividual(int id) {
        return new SuccessDataResult<List<IndividualJobPosition>>(
                this.individualJobPositionDao.getByIndividualIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualJobPosition>> getAllByJobPosition(int id) {
        return null;
    }

    @Override
    public Result add(IndividualJobPosition individualJobPosition)
    {
        individualJobPosition.setCreateDate(LocalDateTime.now());
        individualJobPosition.setActive(true);
        this.individualJobPositionDao.save(individualJobPosition);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualJobPosition individualJobPosition) {
        return null;
    }

    @Override
    public Result delete(IndividualJobPosition individualJobPosition) {
        return null;
    }
}
