package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualJobPosition;

import java.util.List;

public interface IndividualJobPositionService {
    DataResult<List<IndividualJobPosition>> getAll();

    DataResult<IndividualJobPosition> getById(long id);

    DataResult<List<IndividualJobPosition>> getAllByIndividual(int id);

    DataResult<List<IndividualJobPosition>> getAllByJobPosition(int id);

    Result add(IndividualJobPosition individualJobPosition);

    Result update(IndividualJobPosition individualJobPosition);

    Result delete(IndividualJobPosition individualJobPosition);
}