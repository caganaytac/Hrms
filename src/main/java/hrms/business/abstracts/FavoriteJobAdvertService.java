package hrms.business.abstracts;

import java.util.List;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.FavoriteJobAdvert;

public interface FavoriteJobAdvertService extends BaseService<FavoriteJobAdvert, Long> {
    DataResult<List<FavoriteJobAdvert>> getByIndividual(Integer id);
}