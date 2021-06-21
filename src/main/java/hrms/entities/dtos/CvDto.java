package hrms.entities.dtos;

import java.util.List;

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
    private Individual individual;

    private UserPhoto userPhoto;

    private UserBiography userBiography;
    
    private LinkedinAccount linkedinAccount;

    private GithubAccount githubAccount;

    private List<IndividualEducation> individualEducations;

    private List<IndividualJobExperience> individualJobExperiences;

    private List<IndividualTechnology> individualTechnologies;

    private List<IndividualLanguage> individualLanguages;
}