package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<List<City>> getAll();

    DataResult<City> getById(short id);

    Result add(City city);

    Result update(City city);

    Result delete(City city);
}