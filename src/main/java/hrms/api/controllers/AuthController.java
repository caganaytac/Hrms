package hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.AuthService;
import hrms.core.utilities.results.Result;
import hrms.entities.dtos.CorporateRegisterDto;
import hrms.entities.dtos.EmployeeRegisterDto;
import hrms.entities.dtos.IndividualRegisterDto;
import hrms.entities.dtos.UserChangePasswordDto;
import hrms.entities.dtos.UserLoginDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registerForIndividual")
    public ResponseEntity<Result> registerForIndividual(@RequestBody @Valid IndividualRegisterDto individualRegisterDto) {
        Result result = this.authService.registerForIndividual(individualRegisterDto);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/registerForCorporate")
    public ResponseEntity<Result> registerForCorporate(@RequestBody @Valid CorporateRegisterDto corporateRegisterDto) {
        Result result = this.authService.registerForCorporate(corporateRegisterDto);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/registerForEmployee")
    public ResponseEntity<Result> registerForEmployee(@RequestBody @Valid EmployeeRegisterDto employeeRegisterDto) {
        Result result = this.authService.registerForEmployee(employeeRegisterDto);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        Result result = this.authService.login(userLoginDto);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
     
    @PostMapping("/changePassword")
    public ResponseEntity<Result> changePassword(@RequestBody @Valid UserChangePasswordDto userChangePasswordDto) {
        Result result = this.authService.changePassword(userChangePasswordDto);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}