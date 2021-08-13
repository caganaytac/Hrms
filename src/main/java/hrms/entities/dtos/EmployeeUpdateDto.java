package hrms.entities.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import hrms.entities.concretes.Individual;
import hrms.entities.validation.hrmsEmail.HrmsEmail;

public class EmployeeUpdateDto {
    @NotBlank
    private Integer id;

    @NotBlank
    private Integer individualId;

    @NotBlank
    private Integer userId;

    @NotBlank
    @HrmsEmail
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String nationalIdentity;

    @NotBlank
    private LocalDate dateOfBirth;

    @NotBlank
    private Individual individual;

    @NotBlank
    @Length(min = 0, max = 1000)
    private Short creditScore;

    @NotBlank
    private LocalDate startDate;

    @Nullable
    @NotBlank
    private LocalDate finishDate;
}