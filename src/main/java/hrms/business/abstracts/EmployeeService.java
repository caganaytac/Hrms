package hrms.business.abstracts;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.Employee;

public interface EmployeeService extends BaseService<Employee, Integer> {
    DataResult<Employee> getByIndividual(Integer id);

    DataResult<Employee> getByUser(Integer id);
}