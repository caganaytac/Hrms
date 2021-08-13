package hrms.business.abstracts;

import hrms.core.utilities.results.Result;
import hrms.entities.dtos.CorporateRegisterDto;
import hrms.entities.dtos.EmployeeRegisterDto;
import hrms.entities.dtos.IndividualRegisterDto;
import hrms.entities.dtos.UserChangePasswordDto;
import hrms.entities.dtos.UserLoginDto;

public interface AuthService {
    Result registerForIndividual(IndividualRegisterDto individualRegisterDto);

    Result registerForCorporate(CorporateRegisterDto corporateRegisterDto);

    Result registerForEmployee(EmployeeRegisterDto employeeRegisterDto);

    Result login(UserLoginDto userLoginDto);

    Result changePassword(UserChangePasswordDto userChangePasswordDto);
}