package hrms.entities.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import hrms.core.utilities.validation.email.Email;
import hrms.core.utilities.validation.password.Password;

import org.hibernate.validator.constraints.Length;

import hrms.entities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualRegisterDto {
    @NotBlank(message = Messages.emailNotEmpty)
    @Email
    private String email;

    @NotBlank(message = Messages.passwordNotEmpty)
    @Length(min = 8, message = Messages.passwordLength)
    @Password
    private String password;

    @NotBlank(message = Messages.firstNameNotEmpty)
    @Length(min = 2, message = Messages.firstNameLength)
    private String firstName;

    @NotBlank(message = Messages.lastNameNotEmpty)
    @Length(min = 2, message = Messages.lastNameLength)
    private String lastName;

    @NotBlank(message = Messages.nationalIdentityNotEmpty)
    @Length(min = 11, max = 11, message = Messages.nationalIdentityLength)
    private String nationalIdentity;

    @NotNull(message = Messages.dateOfBirthNotEmpty)
    private LocalDate dateOfBirth;
}