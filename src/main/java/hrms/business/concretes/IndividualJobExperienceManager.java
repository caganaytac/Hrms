package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.IndividualJobExperienceService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualJobExperienceDao;
import hrms.entities.concretes.IndividualJobExperience;

@Service
public class IndividualJobExperienceManager implements IndividualJobExperienceService {
    private IndividualJobExperienceDao individualJobExperienceDao;
    private final String INDIVIDUAL_JOB_EXPERIENCE = "Individual job experience";

    @Autowired
    public IndividualJobExperienceManager(IndividualJobExperienceDao individualJobExperienceDao) {
        this.individualJobExperienceDao = individualJobExperienceDao;
    }

    @Override
    public DataResult<List<IndividualJobExperience>> getAll() {
        return new SuccessDataResult<List<IndividualJobExperience>>(this.individualJobExperienceDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualJobExperience> getById(Long id) {
        return new SuccessDataResult<IndividualJobExperience>(
                this.individualJobExperienceDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualJobExperience>> getByIndividual(Integer id) {
        return new SuccessDataResult<List<IndividualJobExperience>>(
                this.individualJobExperienceDao.getByIndividual(id));
    }

    @Override
    public Result add(IndividualJobExperience individualJobExperience) {
        individualJobExperience.setCompanyName(individualJobExperience.getCompanyName().trim());
        Result result = BusinessRules.run(doesExist(individualJobExperience));
        if (result != null)
            return result;

        individualJobExperience.setCreateDate(LocalDateTime.now());
        individualJobExperience.setActive(true);

        this.individualJobExperienceDao.save(individualJobExperience);
        return new SuccessResult(Messages.added(INDIVIDUAL_JOB_EXPERIENCE));
    }

    @Override
    public Result update(IndividualJobExperience individualJobExperience) {
        individualJobExperience.setCompanyName(individualJobExperience.getCompanyName().trim());
        Result result = BusinessRules.run(doesExistById(individualJobExperience.getId()),
                doesExist(individualJobExperience));
        if (result != null)
            return result;

        IndividualJobExperience oldIndividualJobExperience = this.individualJobExperienceDao
                .getByIdAndActive(individualJobExperience.getId(), true);
        individualJobExperience.setIndividual(oldIndividualJobExperience.getIndividual());
        individualJobExperience.setCreateDate(oldIndividualJobExperience.getCreateDate());
        individualJobExperience.setActive(true);

        this.individualJobExperienceDao.save(individualJobExperience);
        return new SuccessResult(Messages.updated(INDIVIDUAL_JOB_EXPERIENCE));
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        IndividualJobExperience oldIndividualJobExperience = this.individualJobExperienceDao.getByIdAndActive(id, true);
        oldIndividualJobExperience.setActive(false);

        this.individualJobExperienceDao.save(oldIndividualJobExperience);
        return new SuccessResult(Messages.deleted(INDIVIDUAL_JOB_EXPERIENCE));
    }

    private Result doesExist(IndividualJobExperience individualJobExperience) {
        Integer individualId = individualJobExperience.getIndividual().getId();
        Short jobPositionId = individualJobExperience.getJobPosition().getId();
        String companyName = individualJobExperience.getCompanyName();
        Short startDate = individualJobExperience.getStartDate();
        Short finishDate = individualJobExperience.getFinishDate();

        IndividualJobExperience result = this.individualJobExperienceDao.doesExist(individualId, jobPositionId, companyName, startDate,
                finishDate);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(INDIVIDUAL_JOB_EXPERIENCE));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        IndividualJobExperience result = this.individualJobExperienceDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(INDIVIDUAL_JOB_EXPERIENCE));
        return new SuccessResult();
    }
}