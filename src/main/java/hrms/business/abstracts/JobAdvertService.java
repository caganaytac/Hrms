package hrms.business.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.JobAdvert;
import hrms.entities.dtos.JobAdvertDto;
import java.util.List;

public interface JobAdvertService extends BaseService<JobAdvert, Long> {
    DataResult<List<JobAdvertDto>> getDetails();

    DataResult<Page<JobAdvertDto>> getDetails(Integer pageNo, Integer pageSize, String[] sortProperties,
            Sort.Direction sortDirection);

    DataResult<JobAdvertDto> getDetailById(Long id);

    DataResult<List<JobAdvert>> getByCorporate(Integer id);
    
    DataResult<List<JobAdvertDto>> getDetailsByCorporate(Integer id);
    
    DataResult<Page<JobAdvertDto>> getDetailsByCorporate(Integer id, Integer pageNo, Integer pageSize, String[] sortProperties,
    Sort.Direction sortDirection);
}