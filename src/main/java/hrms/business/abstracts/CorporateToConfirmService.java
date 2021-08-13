package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.CorporateToConfirm;

public interface CorporateToConfirmService extends BaseService<CorporateToConfirm, Integer> {
    DataResult<CorporateToConfirm> getByCorporate(Integer id);
}