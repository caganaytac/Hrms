package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.WorkTimeService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.WorkTimeDao;
import hrms.entities.concretes.WorkTime;

@Service
public class WorkTimeManager implements WorkTimeService{
    private WorkTimeDao workTimeDao;

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
        workTime.setCreateDate(LocalDateTime.now());
        workTime.setActive(true);

        this.workTimeDao.save(workTime);
        return new SuccessResult();
    }

    @Override
    public Result update(WorkTime workTime) {
        return null;
    }

    @Override
    public Result delete(WorkTime workTime) {
        return null;
    }

}