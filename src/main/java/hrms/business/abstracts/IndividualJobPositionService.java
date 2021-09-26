package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualJobPosition;

import java.util.List;

public interface IndividualJobPositionService extends BaseService<IndividualJobPosition, Long> {
    DataResult<List<IndividualJobPosition>> getByIndividual(Integer id);

    DataResult<List<IndividualJobPosition>> getByJobPosition(Short id);

    Result deleteByIndividual(Integer id);
}