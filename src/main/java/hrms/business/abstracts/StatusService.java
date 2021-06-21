package hrms.business.abstracts;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Status;

import java.util.List;

public interface StatusService {
    DataResult<List<Status>> getAll();

    DataResult<Status> getById(short id);

    Result add(Status status);

    Result update(Status status);

    Result delete(Status status);
}