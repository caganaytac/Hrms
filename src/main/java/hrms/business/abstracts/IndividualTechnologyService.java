package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualTechnology;

public interface IndividualTechnologyService {
    DataResult<List<IndividualTechnology>> getAll();

    DataResult<IndividualTechnology> getById(long id);

    DataResult<List<IndividualTechnology>> getAllByIndividual(int id);

    Result add(IndividualTechnology individualTechnology);

    Result update(IndividualTechnology individualTechnology);

    Result delete(IndividualTechnology individualTechnology);
}