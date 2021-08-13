package hrms.business.concretes;

import hrms.business.abstracts.IndividualEducationService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
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
    private final String INDIVIDUAL_EDUCATION = "Individual education";

    @Autowired
    public IndividualEducationManager(IndividualEducationDao individualEducationDao) {
        this.individualEducationDao = individualEducationDao;
    }

    @Override
    public DataResult<List<IndividualEducation>> getAll() {
        return new SuccessDataResult<List<IndividualEducation>>(this.individualEducationDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualEducation> getById(Long id) {
        return new SuccessDataResult<IndividualEducation>(this.individualEducationDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualEducation>> getByIndividual(Integer id) {
        return new SuccessDataResult<List<IndividualEducation>>(this.individualEducationDao.getByIndividual(id));
    }

    @Override
    public Result add(IndividualEducation individualEducation) {
        Result result = BusinessRules.run(doesExist(individualEducation));
        if (result != null)
            return result;

        individualEducation.setCreateDate(LocalDateTime.now());
        individualEducation.setActive(true);

        this.individualEducationDao.save(individualEducation);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualEducation individualEducation) {
        Result result = BusinessRules.run(doesExistById(individualEducation.getId()), doesExist(individualEducation));
        if (result != null)
            return result;

        IndividualEducation newIndividualEducation = getById(individualEducation.getId()).getData();
        newIndividualEducation.setSchoolFaculty(individualEducation.getSchoolFaculty());
        newIndividualEducation.setStatus(individualEducation.getStatus());
        newIndividualEducation.setGraduateYear(individualEducation.getGraduateYear());

        this.individualEducationDao.save(newIndividualEducation);
        return new SuccessResult();
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        IndividualEducation oldIndividualEducation = this.individualEducationDao.getByIdAndActive(id, true);
        oldIndividualEducation.setActive(false);

        this.individualEducationDao.save(oldIndividualEducation);
        return new SuccessResult();
    }

    private Result doesExist(IndividualEducation individualEducation) {
        Integer individualId = individualEducation.getIndividual().getId();
        Integer schoolFacultyId = individualEducation.getSchoolFaculty().getId();
        Short statusId = individualEducation.getStatus().getId();
        Short graduateYear = individualEducation.getGraduateYear();
        IndividualEducation result = this.individualEducationDao.doesExist(individualId, schoolFacultyId, statusId, graduateYear);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(INDIVIDUAL_EDUCATION));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        IndividualEducation result = this.individualEducationDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(INDIVIDUAL_EDUCATION));
        return new SuccessResult();
    }
}