package hrms.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hrms.entities.concretes.JobAdvert;
import hrms.entities.concretes.UserPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertDto {
    private JobAdvert jobAdvert;

    @JsonIgnoreProperties({"id", "user", "publicId", "createDate", "active"})
    private UserPhoto userPhoto;
}