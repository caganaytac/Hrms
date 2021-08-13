package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualEducation;

import java.util.List;

public interface IndividualEducationService extends BaseService<IndividualEducation , Long> {
    DataResult<List<IndividualEducation>> getByIndividual(Integer id);
}