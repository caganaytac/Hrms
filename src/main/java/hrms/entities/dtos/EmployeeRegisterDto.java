package hrms.entities.dtos;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import hrms.entities.concretes.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterDto {
    @NotBlank
    // @HrmsEmail
    private String email;
    
    @NotBlank
    private String password;

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    private Short creditScore;

    private LocalDate startDate;

    private LocalDate finishDate;

    private String nationalIdentity;
    
    private LocalDate dateOfBirth;

    private List<JobPosition> jobPositions;
}