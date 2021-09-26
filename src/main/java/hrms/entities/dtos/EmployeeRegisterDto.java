package hrms.entities.dtos;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import hrms.core.utilities.validation.callNumber.CallNumber;
import hrms.core.utilities.validation.email.Email;
import hrms.core.utilities.validation.password.Password;
import hrms.entities.concretes.JobPosition;
import hrms.entities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterDto {
    @NotBlank(message = Messages.emailNotEmpty)
    //@HrmsEmail
    @Email
    private String email;

    @NotBlank(message = Messages.passwordNotEmpty)
    @Length(min = 8, message = Messages.passwordLength)
    @Password
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NumberFormat
    private Short creditScore;

    private LocalDate startDate;

    private LocalDate finishDate;

    private String nationalIdentity;

    private LocalDate dateOfBirth;

    private List<JobPosition> jobPositions;

    @NotBlank
    @CallNumber
    private String phoneNumber;

    @Nonnull
    private MultipartFile photo;
}