package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.TechnologyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.TechnologyDao;
import hrms.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyDao technologyDao;

    @Autowired
    public TechnologyManager(TechnologyDao technologyDao) {
        this.technologyDao = technologyDao;
    }

    @Override
    public DataResult<List<Technology>> getAll() {
        return new SuccessDataResult<List<Technology>>(this.technologyDao.getByActive(true));
    }

    @Override
    public DataResult<Technology> getById(Short id) {
        return new SuccessDataResult<Technology>(this.technologyDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(Technology technology) {
        technology.setCreateDate(LocalDateTime.now());
        technology.setActive(true);

        this.technologyDao.save(technology);
        return new SuccessResult();
    }

    @Override
    public Result update(Technology technology) {
        return null;
    }

    @Override
    public Result delete(Technology technology) {
        return null;
    }
}