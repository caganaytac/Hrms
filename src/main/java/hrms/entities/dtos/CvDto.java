package hrms.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hrms.entities.concretes.GithubAccount;
import hrms.entities.concretes.Individual;
import hrms.entities.concretes.IndividualEducation;
import hrms.entities.concretes.IndividualJobExperience;
import hrms.entities.concretes.IndividualLanguage;
import hrms.entities.concretes.IndividualTechnology;
import hrms.entities.concretes.LinkedinAccount;
import hrms.entities.concretes.UserBiography;
import hrms.entities.concretes.UserPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CvDto {
    @JsonIgnoreProperties({"createDate", "active"})
    private Individual individual;

    @JsonIgnoreProperties({"id", "user", "createDate", "active"})
    private UserPhoto userPhoto;
    
    @JsonIgnoreProperties({"id", "user", "createDate", "active"})
    private UserBiography userBiography;
    
    @JsonIgnoreProperties({"id", "user", "createDate", "active"})
    private LinkedinAccount linkedinAccount;
    
    @JsonIgnoreProperties({"id", "user", "createDate", "active"})
    private GithubAccount githubAccount;
    
    @JsonIgnoreProperties({"id", "individual", "createDate", "active"})
    private List<IndividualTechnology> individualTechnologies;
    
    @JsonIgnoreProperties({"id", "individual", "status.id", "createDate", "active"})
    private List<IndividualEducation> individualEducations;
    
    @JsonIgnoreProperties({"id", "individual", "createDate", "active"})
    private List<IndividualJobExperience> individualJobExperiences;
    
    @JsonIgnoreProperties({"id", "individual", "createDate", "active"})
    private List<IndividualLanguage> individualLanguages;
}