package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Corporate;

import java.util.List;

public interface CorporateService {
    DataResult<List<Corporate>> getAll();

    DataResult<Corporate> getById(int id);

    DataResult<List<Corporate>> getAllByUser(int id);

    DataResult<List<Corporate>> getAllByCompanyName(String companyName);

    Result add(Corporate corporate);

    Result update(Corporate corporate);

    Result delete(Corporate corporate);
}