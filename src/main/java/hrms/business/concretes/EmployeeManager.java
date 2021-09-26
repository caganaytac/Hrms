package hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployeeService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.EmployeeDao;
import hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeDao employeeDao;
    private final String EMPLOYEE = "Employee";

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.getByActive(true));
    }

    @Override
    public DataResult<Employee> getById(Integer id) {
        return new SuccessDataResult<Employee>(this.employeeDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<Employee> getByIndividual(Integer id) {
        return new SuccessDataResult<Employee>(this.employeeDao.getByIndividual(id));
    }

    @Override
    public DataResult<Employee> getByUser(Integer id) {
        return new SuccessDataResult<Employee>(this.employeeDao.getByUser(id));
    }

    @Override
    public Result add(Employee employee) {
        Result result = BusinessRules.run(doesExist(employee));
        if (result != null)
            return result;

        employee.setCreateDate(LocalDateTime.now());
        employee.setActive(true);

        this.employeeDao.save(employee);
        return new SuccessResult(Messages.added(EMPLOYEE));
    }

    @Override
    public Result update(Employee employee) {
        Result result = BusinessRules.run(doesExistById(employee.getId()), doesExist(employee));
        if (result != null)
            return result;

        Employee oldEmployee = getById(employee.getId()).getData();
        employee.setIndividual(oldEmployee.getIndividual());
        employee.setCreateDate(oldEmployee.getCreateDate());
        employee.setActive(true);

        this.employeeDao.save(employee);
        return new SuccessResult(Messages.updated(EMPLOYEE));
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Employee oldEmployee = getById(id).getData();
        oldEmployee.setActive(false);

        this.employeeDao.save(oldEmployee);
        return new SuccessResult(Messages.deleted(EMPLOYEE));
    }

    private Result doesExist(Employee employee) {
        Employee result = this.employeeDao.doesExist(employee.getIndividual().getId(), employee.getCreditScore(),
                employee.getStartDate(), employee.getFinishDate());
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(EMPLOYEE));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        Employee result = this.employeeDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(EMPLOYEE));
        return new SuccessResult();
    }
}