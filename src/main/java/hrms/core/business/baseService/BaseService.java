package hrms.core.business.baseService;

import java.util.List;

import javax.validation.Valid;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;

public interface BaseService<TEntity, Id> {
    DataResult<List<TEntity>> getAll();

    DataResult<TEntity> getById(Id id);

    Result add(@Valid TEntity entity);

    Result update(@Valid TEntity entity);

    Result delete(Id id);
}