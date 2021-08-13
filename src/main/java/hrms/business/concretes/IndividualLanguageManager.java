package hrms.business.concretes;

import hrms.business.abstracts.IndividualLanguageService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualLanguageDao;
import hrms.entities.concretes.IndividualLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IndividualLanguageManager implements IndividualLanguageService {
    private IndividualLanguageDao individualLanguageDao;
    private final String INDIVIDUAL_LANGUAGE = "Individual language";

    @Autowired
    public IndividualLanguageManager(IndividualLanguageDao individualLanguageDao) {
        this.individualLanguageDao = individualLanguageDao;
    }

    @Override
    public DataResult<List<IndividualLanguage>> getAll() {
        return new SuccessDataResult<List<IndividualLanguage>>(this.individualLanguageDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualLanguage> getById(Long id) {
        return new SuccessDataResult<IndividualLanguage>(this.individualLanguageDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<List<IndividualLanguage>> getByIndividual(Integer id) {
        return new SuccessDataResult<List<IndividualLanguage>>(this.individualLanguageDao.getByIndividual(id));
    }

    @Override
    public Result add(IndividualLanguage individualLanguage) {
        Result result = BusinessRules.run(doesExist(individualLanguage));
        if (result != null)
            return result;

        individualLanguage.setCreateDate(LocalDateTime.now());
        individualLanguage.setActive(true);

        this.individualLanguageDao.save(individualLanguage);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualLanguage individualLanguage) {
        Result result = BusinessRules.run(doesExistById(individualLanguage.getId()), doesExist(individualLanguage));
        if (result != null)
            return result;

        IndividualLanguage newIndividualLanguage = getById(individualLanguage.getId()).getData();
        newIndividualLanguage.setLanguage(individualLanguage.getLanguage());
        newIndividualLanguage.setLevel(individualLanguage.getLevel());

        this.individualLanguageDao.save(newIndividualLanguage);
        return new SuccessResult();
    }

    @Override
    public Result delete(Long id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        IndividualLanguage oldIndividualLanguage = this.individualLanguageDao.getByIdAndActive(id, true);
        oldIndividualLanguage.setActive(false);

        this.individualLanguageDao.save(oldIndividualLanguage);
        return new SuccessResult();
    }

    private Result doesExist(IndividualLanguage individualLanguage) {
        IndividualLanguage result = this.individualLanguageDao.doesExist(individualLanguage.getLanguage().getId(),
                individualLanguage.getIndividual().getId(), individualLanguage.getLevel());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(INDIVIDUAL_LANGUAGE));
        return new SuccessResult();
    }

    private Result doesExistById(Long id) {
        IndividualLanguage result = this.individualLanguageDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(INDIVIDUAL_LANGUAGE));
        return new SuccessResult();
    }
}