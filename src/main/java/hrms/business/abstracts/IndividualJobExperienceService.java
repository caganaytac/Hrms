package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualJobExperience;

public interface IndividualJobExperienceService {
    DataResult<List<IndividualJobExperience>> getAll();

    DataResult<IndividualJobExperience> getById(long id);

    DataResult<List<IndividualJobExperience>> getAllByIndividual(int id);

    Result add(IndividualJobExperience individualJobExperience);

    Result update(IndividualJobExperience individualJobExperience);

    Result delete(IndividualJobExperience individualJobExperience);
}