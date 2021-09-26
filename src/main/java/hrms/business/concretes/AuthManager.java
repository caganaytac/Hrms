package hrms.business.concretes;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hrms.business.abstracts.AuthService;
import hrms.business.abstracts.CorporateService;
import hrms.business.abstracts.EmployeeService;
import hrms.business.abstracts.IndividualJobPositionService;
import hrms.business.abstracts.IndividualService;
import hrms.business.abstracts.UserService;
import hrms.business.abstracts.UserPhotoService;
import hrms.business.abstracts.PhoneNumberService;
import hrms.business.constans.Messages;
import hrms.core.utilities.email.EmailMessage;
import hrms.core.utilities.email.EmailService;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.entities.concretes.Corporate;
import hrms.entities.concretes.Employee;
import hrms.entities.concretes.Individual;
import hrms.entities.concretes.IndividualJobPosition;
import hrms.entities.concretes.JobPosition;
import hrms.entities.concretes.User;
import hrms.entities.concretes.UserPhoto;
import hrms.entities.concretes.PhoneNumber;
import hrms.entities.dtos.CorporateRegisterDto;
import hrms.entities.dtos.EmployeeRegisterDto;
import hrms.entities.dtos.IndividualRegisterDto;
import hrms.entities.dtos.UserChangePasswordDto;
import hrms.entities.dtos.UserLoginDto;

import java.time.LocalDate;

@Service
public class AuthManager implements AuthService {
    private final UserService userService;
    private final IndividualService individualService;
    private final CorporateService corporateService;
    private final EmployeeService employeeService;
    private final EmailService emailService;
    private final IndividualJobPositionService individualJobPositionService;
    private final PhoneNumberService phoneNumberService;
    private final UserPhotoService userPhotoService;

    @Autowired
    public AuthManager(UserService userService, IndividualService individualService, CorporateService corporateService,
            EmployeeService employeeService, IndividualJobPositionService individualJobPositionService,
            EmailService emailService, PhoneNumberService phoneNumberService, UserPhotoService userPhotoService) {
        this.userService = userService;
        this.emailService = emailService;
        this.phoneNumberService = phoneNumberService;
        this.individualService = individualService;
        this.individualJobPositionService = individualJobPositionService;
        this.corporateService = corporateService;
        this.userPhotoService = userPhotoService;
        this.employeeService = employeeService;
    }

    @Override
    public Result registerForIndividual(IndividualRegisterDto individualRegisterDto) {
        DataResult<User> userToAdd = addUser(individualRegisterDto.getEmail(), individualRegisterDto.getPassword());
        if (!userToAdd.isSuccess())
            return new ErrorResult(userToAdd.getMessage());

        DataResult<Individual> individualToAdd = addIndividual(userToAdd.getData(),
                individualRegisterDto.getFirstName(), individualRegisterDto.getLastName(),
                individualRegisterDto.getNationalIdentity(), individualRegisterDto.getDateOfBirth());
        if (!individualToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(individualToAdd.getMessage());
        }

        if (!individualRegisterDto.getPhoto().isEmpty()) {
            DataResult<UserPhoto> userPhotoToAdd = addUserPhoto(userToAdd.getData(), individualRegisterDto.getPhoto());
            if (!userPhotoToAdd.isSuccess()) {
                this.userService.delete(userToAdd.getData().getId());
                return new ErrorResult(userPhotoToAdd.getMessage());
            }
        }
        
        String text = "We are very happy to see you among us.";
        Result emailToSend = this.sendEmail(userToAdd.getData(), text);
        if (!emailToSend.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(emailToSend.getMessage());
        }
        return new SuccessResult(Messages.registered);
    }

    @Override
    public Result registerForCorporate(CorporateRegisterDto corporateRegisterDto) {
        DataResult<User> userToAdd = addUser(corporateRegisterDto.getEmail(), corporateRegisterDto.getPassword());
        if (!userToAdd.isSuccess())
            return new ErrorResult(userToAdd.getMessage());

        DataResult<Corporate> corporateToAdd = addCorporate(userToAdd.getData(), corporateRegisterDto.getCompanyName(),
                corporateRegisterDto.getWebsite());
        if (!corporateToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(corporateToAdd.getMessage());
        }

        DataResult<PhoneNumber> phoneNumberToAdd = addPhoneNumber(userToAdd.getData(),
                corporateRegisterDto.getPhoneNumber());
        if (!phoneNumberToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(phoneNumberToAdd.getMessage());
        }

        DataResult<UserPhoto> userPhotoToAdd = addUserPhoto(userToAdd.getData(), corporateRegisterDto.getPhoto());
        if (!userPhotoToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(userPhotoToAdd.getMessage());
        }

        String text = "We are very happy to see a wonderful company among us.";
        Result emailToSend = this.sendEmail(userToAdd.getData(), text);
        if (!emailToSend.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(emailToSend.getMessage());
        }
        return new SuccessResult(Messages.registered);
    }

    @Override
    public Result registerForEmployee(EmployeeRegisterDto employeeRegisterDto) {
        DataResult<User> userToAdd = addUser(employeeRegisterDto.getEmail(), employeeRegisterDto.getPassword());
        if (!userToAdd.isSuccess())
            return new ErrorResult(userToAdd.getMessage());

        DataResult<Individual> individualToAdd = addIndividual(userToAdd.getData(), employeeRegisterDto.getFirstName(),
                employeeRegisterDto.getLastName(), employeeRegisterDto.getNationalIdentity(),
                employeeRegisterDto.getDateOfBirth());
        if (!individualToAdd.isSuccess())
            return new ErrorResult(individualToAdd.getMessage());

        Employee employee = new Employee(individualToAdd.getData(), employeeRegisterDto.getCreditScore(),
                employeeRegisterDto.getStartDate(), employeeRegisterDto.getFinishDate());
        Result employeeToAdd = this.employeeService.add(employee);
        if (!employeeToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(employeeToAdd.getMessage());
        }

        IndividualJobPosition individualJobPosition = new IndividualJobPosition();
        individualJobPosition.setIndividual(individualToAdd.getData());
        for (JobPosition jobPosition : employeeRegisterDto.getJobPositions()) {
            individualJobPosition.setJobPosition(jobPosition);
            this.individualJobPositionService.add(individualJobPosition);
        }

        DataResult<PhoneNumber> phoneNumberToAdd = addPhoneNumber(userToAdd.getData(),
                employeeRegisterDto.getPhoneNumber());
        if (!phoneNumberToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(phoneNumberToAdd.getMessage());
        }

        DataResult<UserPhoto> userPhotoToAdd = addUserPhoto(userToAdd.getData(), employeeRegisterDto.getPhoto());
        if (!userPhotoToAdd.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
            return new ErrorResult(userPhotoToAdd.getMessage());
        }

        String text = "We are so lucky to have such a wonderful team member like you among us.";
        Result emailToSend = this.sendEmail(userToAdd.getData(), text);
        if (!emailToSend.isSuccess()) {
            this.userService.delete(userToAdd.getData().getId());
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

    private DataResult<User> addUser(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        Result userToAdd = this.userService.add(newUser);
        if (!userToAdd.isSuccess())
            return new ErrorDataResult<User>(null, userToAdd.getMessage());
        return new SuccessDataResult<User>(newUser);
    }

    private DataResult<Individual> addIndividual(User user, String firstName, String lastName, String nationalIdentity,
            LocalDate dateOfBirth) {
        Individual newIndividual = new Individual(user, firstName, lastName, nationalIdentity, dateOfBirth);
        Result individualToAdd = this.individualService.add(newIndividual);
        if (!individualToAdd.isSuccess())
            return new ErrorDataResult<Individual>(null, individualToAdd.getMessage());
        return new SuccessDataResult<Individual>(newIndividual);
    }

    private DataResult<PhoneNumber> addPhoneNumber(User user, String phoneNumber) {
        PhoneNumber newPhoneNumber = new PhoneNumber(user, phoneNumber);
        Result phoneNumberToAdd = this.phoneNumberService.add(newPhoneNumber);
        if (!phoneNumberToAdd.isSuccess())
            return new ErrorDataResult<PhoneNumber>(null, phoneNumberToAdd.getMessage());
        return new SuccessDataResult<PhoneNumber>(newPhoneNumber);
    }

    private DataResult<UserPhoto> addUserPhoto(User user, MultipartFile file) {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setUser(user);
        Result userPhotoToAdd = this.userPhotoService.add(userPhoto, file);
        if (!userPhotoToAdd.isSuccess())
            return new ErrorDataResult<UserPhoto>(null, userPhotoToAdd.getMessage());
        return new SuccessDataResult<UserPhoto>(userPhoto);
    }

    private DataResult<Corporate> addCorporate(User user, String companyName, String website) {
        Corporate newCorporate = new Corporate(user, companyName, website);
        Result corporateToAdd = this.corporateService.add(newCorporate);
        if (!corporateToAdd.isSuccess())
            return new ErrorDataResult<Corporate>(null, corporateToAdd.getMessage());
        return new SuccessDataResult<Corporate>(newCorporate);
    }

    private Result sendEmail(User user, String text) {
        EmailMessage message = new EmailMessage();
        List<String> toAddresses = Arrays.asList(user.getEmail());
        message.setToAddresses(toAddresses);
        message.setSubject("Welcome to Hrms!");
        message.setText("<p>" + text + " <a href=\"http://localhost:3000/account/confirm/id=" + user.getId()
                + "/\" target=\"_blank\">Click to confirm your account!</a></p>");
        Result result = this.emailService.send(message);
        if (!result.isSuccess())
            return new ErrorResult(result.getMessage());
        return new SuccessResult();
    }
}