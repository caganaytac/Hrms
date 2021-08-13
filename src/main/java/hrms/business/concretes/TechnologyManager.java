package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.TechnologyService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.TechnologyDao;
import hrms.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyDao technologyDao;
    private final String TECHNOLOGY = "Technology";

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
        technology.setName(technology.getName().trim());
        Result result = BusinessRules.run(doesExist(technology));
        if (result != null)
            return result;

        technology.setCreateDate(LocalDateTime.now());
        technology.setActive(true);

        this.technologyDao.save(technology);
        return new SuccessResult(Messages.added(TECHNOLOGY));
    }

    @Override
    public Result update(Technology technology) {
        technology.setName(technology.getName().trim());
        Result result = BusinessRules.run(doesExistById(technology.getId()), doesExist(technology));
        if (result != null)
            return result;

        Technology newTechnology = this.technologyDao.getByIdAndActive(technology.getId(), true);
        newTechnology.setName(technology.getName());

        this.technologyDao.save(newTechnology);
        return new SuccessResult(Messages.updated(TECHNOLOGY));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Technology oldTechnology = this.technologyDao.getByIdAndActive(id, true);
        oldTechnology.setActive(false);

        this.technologyDao.save(oldTechnology);
        return new SuccessResult(Messages.deleted(TECHNOLOGY));
    }

    private Result doesExist(Technology technology) {
        Technology result = this.technologyDao.getByName(technology.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(TECHNOLOGY));
        return new SuccessResult();
    }

    private Result doesExistById(Short id) {
        Technology result = this.technologyDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(TECHNOLOGY));
        return new SuccessResult();
    }
}