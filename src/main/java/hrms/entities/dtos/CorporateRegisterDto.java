package hrms.entities.dtos;

import javax.validation.constraints.NotBlank;

import hrms.core.utilities.validation.email.Email;
import hrms.core.utilities.validation.password.Password;
import hrms.core.utilities.validation.website.Website;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporateRegisterDto {
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Password
    private String password;
    
    @NotBlank
    private String companyName;
    
    @NotBlank
    @Website
    private String website;
}