package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.WorkAreaService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.WorkAreaDao;
import hrms.entities.concretes.WorkArea;

@Service
public class WorkAreaManager implements WorkAreaService {
    private WorkAreaDao workAreaDao;
    private final String WORK_AREA = "Work area";

    @Autowired
    public WorkAreaManager(WorkAreaDao workAreaDao) {
        this.workAreaDao = workAreaDao;
    }

    @Override
    public DataResult<List<WorkArea>> getAll() {
        return new SuccessDataResult<List<WorkArea>>(this.workAreaDao.getByActive(true));
    }

    @Override
    public DataResult<WorkArea> getById(Short id) {
        return new SuccessDataResult<WorkArea>(this.workAreaDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(WorkArea workArea) {
        workArea.setName(workArea.getName().trim());
        Result result = BusinessRules.run(doesExist(workArea));
        if (result != null)
            return result;

        workArea.setActive(true);
        workArea.setCreateDate(LocalDateTime.now());

        this.workAreaDao.save(workArea);
        return new SuccessResult(Messages.added(WORK_AREA));
    }

    @Override
    public Result update(WorkArea workArea) {
        workArea.setName(workArea.getName().trim());
        Result result = BusinessRules.run(doesExistById(workArea.getId()), doesExist(workArea));
        if (result != null)
            return result;

        WorkArea newWorkArea = this.workAreaDao.getByIdAndActive(workArea.getId(), true);
        newWorkArea.setName(workArea.getName());

        this.workAreaDao.save(newWorkArea);
        return new SuccessResult(Messages.updated(WORK_AREA));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        WorkArea oldWorkArea = this.workAreaDao.getByIdAndActive(id, true);
        oldWorkArea.setActive(false);

        this.workAreaDao.save(oldWorkArea);
        return new SuccessResult(Messages.deleted(WORK_AREA));
    }

    private Result doesExist(WorkArea workArea) {
        WorkArea result = this.workAreaDao.getByName(workArea.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(WORK_AREA));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        WorkArea result = this.workAreaDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(WORK_AREA));
        return new SuccessResult();
    }
}