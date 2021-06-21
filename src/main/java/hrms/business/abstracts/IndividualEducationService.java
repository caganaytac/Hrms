package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualEducation;

import java.util.List;

public interface IndividualEducationService {
    DataResult<List<IndividualEducation>> getAll();

    DataResult<IndividualEducation> getById(long id);

    Result add(IndividualEducation individualEducation);

    Result update(IndividualEducation individualEducation);

    Result delete(IndividualEducation individualEducation);
}