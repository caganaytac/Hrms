package hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

import hrms.entities.concretes.User;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPhotoDto {
    @NotNull
    private User user;
    
    @NotNull
    private MultipartFile file;
}