package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Language;

import java.util.List;

public interface LanguageService {
    DataResult<List<Language>> getAll();

    DataResult<Language> getById(short id);

    Result add(Language language);

    Result update(Language language);

    Result delete(Language language);
}