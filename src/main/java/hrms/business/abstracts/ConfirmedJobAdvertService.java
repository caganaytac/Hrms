package hrms.business.abstracts;

import java.util.List;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.ConfirmedJobAdvert;

public interface ConfirmedJobAdvertService extends BaseService<ConfirmedJobAdvert, Long> {
    DataResult<ConfirmedJobAdvert> getByJobAdvert(Long id);

    DataResult<List<ConfirmedJobAdvert>> getByEmployee(Integer id);
}