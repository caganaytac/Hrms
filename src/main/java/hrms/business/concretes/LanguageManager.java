package hrms.business.concretes;

import hrms.business.abstracts.LanguageService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.LanguageDao;
import hrms.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    private LanguageDao languageDao;
    private final String LANGUAGE = "Language";

    @Autowired
    public LanguageManager(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public DataResult<List<Language>> getAll() {
        return new SuccessDataResult<List<Language>>(this.languageDao.getByActive(true));
    }

    @Override
    public DataResult<Language> getById(Short id) {
        return new SuccessDataResult<Language>(this.languageDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Language language) {
        language.setName(language.getName().trim());
        Result result = BusinessRules.run(doesExist(language));
        if (result != null)
            return result;

        language.setCreateDate(LocalDateTime.now());
        language.setActive(true);

        this.languageDao.save(language);
        return new SuccessResult(Messages.added(LANGUAGE));
    }

    @Override
    public Result update(Language language) {
        language.setName(language.getName().trim());
        Result result = BusinessRules.run(doesExistById(language.getId()), doesExist(language));
        if (result != null)
            return result;

        Language newLanguage = getById(language.getId()).getData();
        newLanguage.setName(language.getName());

        this.languageDao.save(newLanguage);
        return new SuccessResult(Messages.updated(LANGUAGE));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Language oldLanguage = this.languageDao.getByIdAndActive(id, true);
        oldLanguage.setActive(false);

        this.languageDao.save(oldLanguage);
        return new SuccessResult(Messages.deleted(LANGUAGE));
    }

    private Result doesExist(Language language) {
        Language result = this.languageDao.getByName(language.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(LANGUAGE));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        Language result = this.languageDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(LANGUAGE));
        return new SuccessResult();
    }
}