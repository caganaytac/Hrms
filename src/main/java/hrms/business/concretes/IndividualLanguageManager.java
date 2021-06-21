package hrms.business.concretes;

import hrms.business.abstracts.IndividualLanguageService;
import hrms.core.utilities.results.DataResult;
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

    @Autowired
    public IndividualLanguageManager(IndividualLanguageDao individualLanguageDao) {
        this.individualLanguageDao = individualLanguageDao;
    }

    @Override
    public DataResult<List<IndividualLanguage>> getAll() {
        return new SuccessDataResult<List<IndividualLanguage>>(this.individualLanguageDao.getByActive(true));
    }

    @Override
    public DataResult<IndividualLanguage> getById(long id) {
        return new SuccessDataResult<IndividualLanguage>(this.individualLanguageDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(IndividualLanguage individualLanguage) {
        individualLanguage.setCreateDate(LocalDateTime.now());
        individualLanguage.setActive(true);
        this.individualLanguageDao.save(individualLanguage);
        return new SuccessResult();
    }

    @Override
    public Result update(IndividualLanguage individualLanguage) {
        return null;
    }

    @Override
    public Result delete(IndividualLanguage individualLanguage) {
        return null;
    }
}
