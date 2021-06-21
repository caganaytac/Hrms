package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualLanguage;

import java.util.List;

public interface IndividualLanguageService {
    DataResult<List<IndividualLanguage>> getAll();

    DataResult<IndividualLanguage> getById(long id);

    Result add(IndividualLanguage individualLanguage);

    Result update(IndividualLanguage individualLanguage);

    Result delete(IndividualLanguage individualLanguage);
}