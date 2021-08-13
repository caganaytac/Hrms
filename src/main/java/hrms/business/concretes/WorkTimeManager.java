package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.WorkTimeService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.WorkTimeDao;
import hrms.entities.concretes.WorkTime;

@Service
public class WorkTimeManager implements WorkTimeService {
    private WorkTimeDao workTimeDao;
    private final String WORK_TIME = "Work time";

    @Autowired
    public WorkTimeManager(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }

    @Override
    public DataResult<List<WorkTime>> getAll() {
        return new SuccessDataResult<List<WorkTime>>(this.workTimeDao.getByActive(true));
    }

    @Override
    public DataResult<WorkTime> getById(Short id) {
        return new SuccessDataResult<WorkTime>(this.workTimeDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(WorkTime workTime) {
        workTime.setName(workTime.getName().trim());
        Result result = BusinessRules.run(doesExist(workTime));
        if (result != null)
            return result;

        workTime.setCreateDate(LocalDateTime.now());
        workTime.setActive(true);

        this.workTimeDao.save(workTime);
        return new SuccessResult(Messages.added(WORK_TIME));
    }

    @Override
    public Result update(WorkTime workTime) {
        workTime.setName(workTime.getName().trim());
        Result result = BusinessRules.run(doesExistById(workTime.getId()), doesExist(workTime));
        if (result != null)
            return result;

        WorkTime newWorkTime = this.workTimeDao.getByIdAndActive(workTime.getId(), true);
        newWorkTime.setName(workTime.getName());

        this.workTimeDao.save(newWorkTime);
        return new SuccessResult(Messages.updated(WORK_TIME));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        WorkTime oldWorkTime = this.workTimeDao.getByIdAndActive(id, true);
        oldWorkTime.setActive(false);

        this.workTimeDao.save(oldWorkTime);
        return new SuccessResult(Messages.deleted(WORK_TIME));
    }

    private Result doesExist(WorkTime workTime) {
        WorkTime result = this.workTimeDao.getByName(workTime.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(WORK_TIME));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        WorkTime result = this.workTimeDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(WORK_TIME));
        return new SuccessResult();
    }
}