package hrms.business.concretes;

import hrms.business.abstracts.CityService;
import hrms.business.constans.Messages;
import hrms.core.business.baseService.BaseManager;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.CityDao;
import hrms.entities.concretes.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CityManager extends BaseManager<CityDao, City, Short> implements CityService {
    private CityDao cityDao;
    private final String CITY = "City";

    @Autowired
    public CityManager(CityDao cityDao) {
        super(cityDao, "City");
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.getByActive(true));
    }

    @Override
    public DataResult<City> getById(Short id) {
        return new SuccessDataResult<City>(this.cityDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(City city) {
        city.setName(city.getName().trim());
        Result result = BusinessRules.run(doesExist(city));
        if (result != null)
            return result;

        city.setCreateDate(LocalDateTime.now());
        city.setActive(true);

        this.cityDao.save(city);
        return new SuccessResult(Messages.added(CITY));
    }

    @Override
    public Result update(City city) {
        city.setName(city.getName().trim());
        Result result = BusinessRules.run(doesExistById(city.getId()), doesExist(city));
        if (result != null)
            return result;

        City newCity = getById(city.getId()).getData();
        newCity.setName(city.getName());

        this.cityDao.save(newCity);
        return new SuccessResult(Messages.updated(CITY));
    }

    @Override
    public Result delete(Short id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        City oldCity = getById(id).getData();
        oldCity.setActive(false);

        this.cityDao.save(oldCity);
        return new SuccessResult(Messages.deleted(CITY));
    }

    private Result doesExist(City city) {
        City result = this.cityDao.getByName(city.getName());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(CITY));
        return new SuccessResult();
    }
}