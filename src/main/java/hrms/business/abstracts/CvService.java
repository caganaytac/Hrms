package hrms.business.abstracts;

import java.util.List;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.dtos.CvDto;

public interface CvService {
    DataResult<List<CvDto>> getAll();

    DataResult<CvDto> getByIndividual(Integer id);

    Result add(CvDto cvDto);

    Result update(CvDto cvDto);

    Result delete(CvDto cvDto);   
}