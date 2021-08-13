package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Corporate;

public interface CorporateService extends BaseService<Corporate, Integer> {
    DataResult<Corporate> getByUser(Integer id);

    Result confirm(Corporate corporate);
}