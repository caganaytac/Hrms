package hrms.api.controllers;

import hrms.business.abstracts.EmployeeService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.Employee;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController extends BaseController<EmployeeService, Employee, Integer>{
    public EmployeesController(EmployeeService employeeService) {
        super(employeeService);
    }
}