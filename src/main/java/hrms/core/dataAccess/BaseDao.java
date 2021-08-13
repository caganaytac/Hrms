package hrms.core.dataAccess;

import java.util.List;

public interface BaseDao<TEntity, Id> {
    List<TEntity> getByActive(boolean active);

    TEntity getByIdAndActive(Id id, boolean active);
}