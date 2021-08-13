package hrms.entities.dtos;

import javax.validation.constraints.NotBlank;

public class UserChangePasswordDto {
    @NotBlank
    private Integer id;

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}