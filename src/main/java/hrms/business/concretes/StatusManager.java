package hrms.business.concretes;

import hrms.business.abstracts.StatusService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
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
    private final String STATUS = "Status";

    @Autowired
    public StatusManager(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public DataResult<List<Status>> getAll() {
        return new SuccessDataResult<List<Status>>(this.statusDao.getByActive(true));
    }

    @Override
    public DataResult<Status> getById(Short id) {
        return new SuccessDataResult<Status>(this.statusDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Status status) {
        status.setName(status.getName().trim());
        Result result = BusinessRules.run(doesExist(status));
        if (result != null)
            return result;

        status.setCreateDate(LocalDateTime.now());
        status.setActive(true);

        this.statusDao.save(status);
        return new SuccessResult(Messages.added(STATUS));
    }

    @Override
    public Result update(Status status) {
        status.setName(status.getName().trim());
        Result result = BusinessRules.run(doesExistById(status.getId()), doesExist(status));
        if (result != null)
            return result;

        Status newStatus = getById(status.getId()).getData();
        newStatus.setName(status.getName());

        this.statusDao.save(status);
        return new SuccessResult(Messages.updated(STATUS));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Status oldStatus = this.statusDao.getByIdAndActive(id, true);
        oldStatus.setActive(false);

        this.statusDao.save(oldStatus);
        return new SuccessResult(Messages.deleted(STATUS));
    }

    private Result doesExist(Status status) {
        Status result = this.statusDao.getByName(status.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(STATUS));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        Status result = this.statusDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(STATUS));
        return new SuccessResult();
    }
}