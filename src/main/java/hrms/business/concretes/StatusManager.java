package hrms.business.concretes;

import hrms.business.abstracts.StatusService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.StatusDao;
import hrms.entities.concretes.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusManager implements StatusService {
    private StatusDao statusDao;

    @Autowired
    public StatusManager(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public DataResult<List<Status>> getAll() {
        return new SuccessDataResult<>(this.statusDao.getByActive(true));
    }

    @Override
    public DataResult<Status> getById(short id) {
        return new SuccessDataResult<>(this.statusDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Status status) {
        status.setCreateDate(LocalDateTime.now());
        status.setActive(true);
        this.statusDao.save(status);
        return new SuccessResult();
    }

    @Override
    public Result update(Status status) {
        return null;
    }

    @Override
    public Result delete(Status status) {
        return null;
    }
}
