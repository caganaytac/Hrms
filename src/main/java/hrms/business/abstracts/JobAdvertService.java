package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.JobAdvert;
import hrms.entities.dtos.JobAdvertDto;
import java.util.List;

public interface JobAdvertService extends BaseService<JobAdvert, Long> {
    DataResult<List<JobAdvertDto>> getDetails();

    DataResult<JobAdvertDto> getDetailById(Long id);

    DataResult<List<JobAdvert>> getByCorporate(Integer id);

    DataResult<List<JobAdvertDto>> getDetailsByCorporate(Integer id);
}