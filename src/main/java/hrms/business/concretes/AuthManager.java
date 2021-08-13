package hrms.business.concretes;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.AuthService;
import hrms.business.abstracts.CorporateService;
import hrms.business.abstracts.EmployeeService;
import hrms.business.abstracts.IndividualJobPositionService;
import hrms.business.abstracts.IndividualService;
import hrms.business.abstracts.UserService;
import hrms.business.constans.Messages;
import hrms.core.utilities.email.EmailMessage;
import hrms.core.utilities.email.EmailService;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessResult;
import hrms.entities.concretes.Corporate;
import hrms.entities.concretes.Employee;
import hrms.entities.concretes.Individual;
import hrms.entities.concretes.IndividualJobPosition;
import hrms.entities.concretes.JobPosition;
import hrms.entities.concretes.User;
import hrms.entities.dtos.CorporateRegisterDto;
import hrms.entities.dtos.EmployeeRegisterDto;
import hrms.entities.dtos.IndividualRegisterDto;
import hrms.entities.dtos.UserChangePasswordDto;
import hrms.entities.dtos.UserLoginDto;

@Service
public class AuthManager implements AuthService {
    private final UserService userService;
    private final IndividualService individualService;
    private final CorporateService corporateService;
    private final EmployeeService employeeService;
    private final EmailService emailService;
    private final IndividualJobPositionService individualJobPositionService;

    @Autowired
    public AuthManager(UserService userService, IndividualService individualService, CorporateService corporateService,
            EmployeeService employeeService, IndividualJobPositionService individualJobPositionService, EmailService emailService) {
        this.userService = userService;
        this.individualService = individualService;
        this.corporateService = corporateService;
        this.employeeService = employeeService;
        this.individualJobPositionService = individualJobPositionService;
        this.emailService = emailService;
    }

    @Override
    public Result registerForIndividual(IndividualRegisterDto individualRegisterDto) {
        User user = new User();
        user.setEmail(individualRegisterDto.getEmail());
        user.setPassword(individualRegisterDto.getPassword());
        Result userToAdd = this.userService.add(user);
        if (!userToAdd.isSuccess())
            return new ErrorResult(userToAdd.getMessage());

        Individual individual = new Individual(user, individualRegisterDto.getFirstName(),
                individualRegisterDto.getLastName(), individualRegisterDto.getNationalIdentity(),
                individualRegisterDto.getDateOfBirth());
        Result individualToAdd = this.individualService.add(individual);
        if (!individualToAdd.isSuccess()) {
            this.userService.delete(user.getId());
            return new ErrorResult(individualToAdd.getMessage());
        }

        String text = "We are very happy to see you among us.";
        Result emailToSend = this.sendEmail(user, text);
        if (!emailToSend.isSuccess()) {
            this.individualService.delete(individual.getId());
            this.userService.delete(user.getId());
            return new ErrorResult(emailToSend.getMessage());
        }
        return new SuccessResult(Messages.registered);
    }

    @Override
    public Result registerForCorporate(CorporateRegisterDto corporateRegisterDto) {
        User user = new User();
        user.setEmail(corporateRegisterDto.getEmail());
        user.setPassword(corporateRegisterDto.getPassword());
        Result userToAdd = this.userService.add(user);
        if (!userToAdd.isSuccess())
            return new ErrorResult(userToAdd.getMessage());

        Corporate corporate = new Corporate();
        corporate.setUser(user);
        corporate.setCompanyName(corporateRegisterDto.getCompanyName());
        corporate.setWebsite(corporateRegisterDto.getWebsite());
        Result corporateToAdd = this.corporateService.add(corporate);
        if (!corporateToAdd.isSuccess()) {
            this.userService.delete(user.getId());
            return new ErrorResult(corporateToAdd.getMessage());
        }

        String text = "We are very happy to see a wonderful company among us.";
        Result emailToSend = this.sendEmail(user, text);
        if (!emailToSend.isSuccess()) {
            this.corporateService.delete(corporate.getId());
            this.userService.delete(user.getId());
            return new ErrorResult(emailToSend.getMessage());
        }
        return new SuccessResult(Messages.registered);
    }

    @Override
    public Result registerForEmployee(EmployeeRegisterDto employeeRegisterDto) {
        User user = new User();
        user.setEmail(employeeRegisterDto.getEmail());
        user.setPassword(employeeRegisterDto.getPassword());
        Result userToAdd = this.userService.add(user);
        if (!userToAdd.isSuccess())
            return new ErrorResult(userToAdd.getMessage());

        Individual individual = new Individual(user, employeeRegisterDto.getFirstName(),
                employeeRegisterDto.getLastName(), employeeRegisterDto.getNationalIdentity(),
                employeeRegisterDto.getDateOfBirth());
        Result individualToAdd = this.individualService.add(individual);
        if (!individualToAdd.isSuccess()) {
            this.userService.delete(user.getId());
            return new ErrorResult(individualToAdd.getMessage());
        }

        Employee employee = new Employee(individual, employeeRegisterDto.getCreditScore(),
                employeeRegisterDto.getStartDate(), employeeRegisterDto.getFinishDate());
        Result employeeToAdd = this.employeeService.add(employee);
        if (!employeeToAdd.isSuccess()) {
            this.individualService.delete(individual.getId());
            this.userService.delete(user.getId());
            return new ErrorResult(employeeToAdd.getMessage());
        }

        IndividualJobPosition individualJobPosition = new IndividualJobPosition();
        individualJobPosition.setIndividual(individual);
        for (JobPosition jobPosition : employeeRegisterDto.getJobPositions()) {
            individualJobPosition.setJobPosition(jobPosition);
            this.individualJobPositionService.add(individualJobPosition);
        }

        String text = "We are so lucky to have such a wonderful team member like you among us.";
        Result emailToSend = this.sendEmail(user, text);
        if (!emailToSend.isSuccess()) {
            this.employeeService.delete(employee.getId());
            this.individualService.delete(individual.getId());
            this.userService.delete(user.getId());
            return new ErrorResult(emailToSend.getMessage());
        }
        return new SuccessResult(Messages.registered);
    }

    @Override
    public Result login(UserLoginDto userLoginDto) {
        return new SuccessResult();
    }

    @Override
    public Result changePassword(UserChangePasswordDto userChangePasswordDto) {
        return new SuccessResult();
    }

    private Result sendEmail(User user, String text) {
        EmailMessage message = new EmailMessage();
        List<String> toAddresses = Arrays.asList(user.getEmail());
        message.setToAddresses(toAddresses);
        message.setSubject("Welcome to Hrms!");
        message.setText("<p>" + text + " <a href=\"http://localhost:8080/api/users/confirm?id=" + user.getId()
                + "\" target=\"_blank\">Click to confirm your account!</a></p>");
        Result result = this.emailService.send(message);
        if (!result.isSuccess())
            return new ErrorResult(result.getMessage());
        return new SuccessResult();
    }
}