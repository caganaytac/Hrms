package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualLanguage;

import java.util.List;

public interface IndividualLanguageService extends BaseService<IndividualLanguage, Long> {
    DataResult<List<IndividualLanguage>> getByIndividual(Integer id);
}