package hrms.business.concretes;

import hrms.business.abstracts.IndividualEducationService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualEducationDao;
import hrms.entities.concretes.IndividualEducation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IndividualEducationManager implements IndividualEducationService {
    private IndividualEducationDao individualEducationDao;

    @Autowired
    public IndividualEducationManager(IndividualEducationDao individualEducationDao) {
        this.individualEducationDao = individualEducationDao;
    }

    @Override
    public DataResult<List<IndividualEducation>> getAll() {
        return new SuccessDataResult<List<IndividualEducation>>(this.individualEducationDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualEducation> getById(long id) {
        return new SuccessDataResult<IndividualEducation>(this.individualEducationDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(IndividualEducation individualEducation) {

        individualEducation.setCreateDate(LocalDateTime.now());
        individualEducation.setActive(true);
        this.individualEducationDao.save(individualEducation);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualEducation individualEducation) {
        return null;
    }

    @Override
    public Result delete(IndividualEducation individualEducation) {
        return null;
    }
}
