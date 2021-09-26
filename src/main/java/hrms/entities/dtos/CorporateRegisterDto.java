package hrms.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import hrms.core.utilities.validation.callNumber.CallNumber;
import hrms.core.utilities.validation.email.Email;
import hrms.core.utilities.validation.password.Password;
import hrms.core.utilities.validation.website.Website;
import hrms.entities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporateRegisterDto {
    @NotBlank(message = Messages.emailNotEmpty)
    @Email
    private String email;

    @NotBlank(message = Messages.passwordNotEmpty)
    @Length(min = 8, message = Messages.passwordLength)
    @Password
    private String password;
    
    @NotBlank
    private String companyName;
    
    @NotBlank
    @Website
    private String website;

    @NotBlank
    @CallNumber
    private String phoneNumber;

    @NotNull
    private MultipartFile photo;
}