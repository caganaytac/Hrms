package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.Individual;
import hrms.entities.dtos.CvDto;

import java.util.List;

public interface IndividualService extends BaseService<Individual, Integer> {
    DataResult<Individual> getByUser(Integer id);

    DataResult<List<CvDto>> getCv(Integer id);
}