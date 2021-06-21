package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.WorkAreaService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.WorkAreaDao;
import hrms.entities.concretes.WorkArea;

@Service
public class WorkAreaManager implements WorkAreaService{
    private WorkAreaDao workAreaDao;

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
        workArea.setActive(true);
        workArea.setCreateDate(LocalDateTime.now());
        
        this.workAreaDao.save(workArea);
        return new SuccessResult();
    }

    @Override
    public Result update(WorkArea workArea) {
        return null;
    }

    @Override
    public Result delete(WorkArea workArea) {
        return null;
    }  
}