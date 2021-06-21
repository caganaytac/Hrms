package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Technology;

public interface TechnologyService {
    DataResult<List<Technology>> getAll();

    DataResult<Technology> getById(Short id);

    Result add(Technology technology);

    Result update(Technology technology);

    Result delete(Technology technology);
}
