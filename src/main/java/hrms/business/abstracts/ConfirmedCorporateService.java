package hrms.business.abstracts;

import hrms.entities.concretes.ConfirmedCorporate;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;

public interface ConfirmedCorporateService {
    DataResult<List<ConfirmedCorporate>> getAll();

    DataResult<ConfirmedCorporate> getById(Long id);

    DataResult<List<ConfirmedCorporate>> getByCorporate(Integer id);

    DataResult<List<ConfirmedCorporate>> getByEmployee(Integer id);

    Result add(ConfirmedCorporate confirmedCorporate);
}