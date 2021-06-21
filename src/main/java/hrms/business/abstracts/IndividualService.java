package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Individual;
// import hrms.entities.dtos.CvDto;

import java.util.List;

public interface IndividualService {
    DataResult<List<Individual>> getAll();

    DataResult<Individual> getById(int id);

    // DataResult<CvDto> getCv(int id);

    Result add(Individual individual);

    Result update(Individual individual);

    Result delete(Individual individual);
}