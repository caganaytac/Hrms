package hrms.business.concretes;

import hrms.business.abstracts.LanguageService;
import hrms.core.utilities.results.DataResult;
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

    @Autowired
    public LanguageManager(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public DataResult<List<Language>> getAll() {
        return new SuccessDataResult<List<Language>>(this.languageDao.getByActive(true));
    }

    @Override
    public DataResult<Language> getById(short id) {
        return new SuccessDataResult<Language>(this.languageDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Language language) {
        language.setCreateDate(LocalDateTime.now());
        language.setActive(true);
        this.languageDao.save(language);
        return new SuccessResult();
    }

    @Override
    public Result update(Language language) {
        return null;
    }

    @Override
    public Result delete(Language language) {
        return null;
    }
}
