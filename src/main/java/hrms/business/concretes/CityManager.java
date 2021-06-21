package hrms.business.concretes;

import hrms.business.abstracts.CityService;
import hrms.core.utilities.results.DataResult;
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
public class CityManager implements CityService {
    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<>(this.cityDao.getByActive(true));
    }

    @Override
    public DataResult<City> getById(short id) {
        return new SuccessDataResult<>(this.cityDao.getByIdAndActive(id, true));
    }

    @Override
    public Result add(City city) {
        city.setCreateDate(LocalDateTime.now());
        city.setActive(true);
        this.cityDao.save(city);
        return new SuccessResult();
    }

    @Override
    public Result update(City city) {
        return null;
    }

    @Override
    public Result delete(City city) {
        return null;
    }
}
