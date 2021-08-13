package hrms.business.abstracts;

import java.util.List;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualJobExperience;

public interface IndividualJobExperienceService extends BaseService<IndividualJobExperience, Long> {
    DataResult<List<IndividualJobExperience>> getByIndividual(Integer id);
}