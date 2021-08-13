package hrms.business.abstracts;

import java.util.List;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualTechnology;

public interface IndividualTechnologyService extends BaseService<IndividualTechnology, Long> {
    DataResult<List<IndividualTechnology>> getByIndividual(Integer id);
}