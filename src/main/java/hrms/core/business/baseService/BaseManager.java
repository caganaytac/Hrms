package hrms.core.business.baseService;

import org.springframework.beans.factory.annotation.Autowired;

import hrms.business.constans.Messages;
import hrms.core.dataAccess.BaseDao;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessResult;

public class BaseManager<TEntityDao extends BaseDao<TEntity, Id>, TEntity, Id> {
    private final TEntityDao entityDao;
    private final String entityName;
    
    @Autowired
    public BaseManager(final TEntityDao entityDao, final String entityName) {
        this.entityDao = entityDao;
        this.entityName = entityName;
    }

    // public DataResult<List<TEntity>> getAll() {
    //     return new SuccessDataResult<List<TEntity>>(this.entityDao.getByActive(true));
    // }

    // public DataResult<TEntity> getById(Id id) {
    //     return new SuccessDataResult<TEntity>(this.entityDao.getByIdAndActive(id, true));
    // }

    public Result doesExistById(Id id) {
        TEntity result = this.entityDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(entityName));
        return new SuccessResult();
    }
}