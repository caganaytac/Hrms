package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.CvService;
import hrms.business.abstracts.GithubAccountService;
import hrms.business.abstracts.IndividualEducationService;
import hrms.business.abstracts.IndividualJobExperienceService;
import hrms.business.abstracts.IndividualLanguageService;
import hrms.business.abstracts.IndividualService;
import hrms.business.abstracts.IndividualTechnologyService;
import hrms.business.abstracts.LinkedinAccountService;
import hrms.business.abstracts.UserBiographyService;
import hrms.business.abstracts.UserPhotoService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.entities.dtos.CvDto;
import lombok.var;

@Service
public class CvManager implements CvService {

    private IndividualService individualService;
    private UserPhotoService userPhotoService;
    private UserBiographyService userBiographyService;
    private LinkedinAccountService linkedinAccountService;
    private GithubAccountService githubAccountService;
    private IndividualTechnologyService individualTechnologyService;
    private IndividualEducationService individualEducationService;
    private IndividualJobExperienceService individualJobExperienceService;
    private IndividualLanguageService individualLanguageService;


    @Autowired
    public CvManager(IndividualService individualService, UserPhotoService userPhotoService,
            UserBiographyService userBiographyService, LinkedinAccountService linkedinAccountService,
            GithubAccountService githubAccountService, IndividualTechnologyService individualTechnologyService,
            IndividualEducationService individualEducationService,
            IndividualJobExperienceService individualJobExperienceService,
            IndividualLanguageService individualLanguageService) {
        this.individualService = individualService;
        this.userPhotoService = userPhotoService;
        this.userBiographyService = userBiographyService;
        this.linkedinAccountService = linkedinAccountService;
        this.githubAccountService = githubAccountService;
        this.individualTechnologyService = individualTechnologyService;
        this.individualEducationService = individualEducationService;
        this.individualJobExperienceService = individualJobExperienceService;
        this.individualLanguageService = individualLanguageService;
    }

    @Override
    public DataResult<List<CvDto>> getAll() {
        return new SuccessDataResult<List<CvDto>>();
    }

    @Override
    public DataResult<CvDto> getByIndividual(Integer id) {
        //without join
        var cvDto = new CvDto();
        var individual = this.individualService.getById(id).getData();
        cvDto.setIndividual(individual);
        
        var userPhoto = this.userPhotoService.getByUser(individual.getUser().getId()).getData();
        cvDto.setUserPhoto(userPhoto);

        var userBiography = this.userBiographyService.getByUser(individual.getUser().getId()).getData();
        cvDto.setUserBiography(userBiography);

        var linkedinAccount = this.linkedinAccountService.getByUser(individual.getUser().getId()).getData();
        cvDto.setLinkedinAccount(linkedinAccount);

        var githubAccount = this.githubAccountService.getByUser(individual.getUser().getId()).getData();
        cvDto.setGithubAccount(githubAccount);

        var individualTechnologies = this.individualTechnologyService.getByIndividual(individual.getId()).getData();
        cvDto.setIndividualTechnologies(individualTechnologies);

        var individualEducations = this.individualEducationService.getByIndividual(individual.getId()).getData();
        cvDto.setIndividualEducations(individualEducations);

        var individualJobExperiences = this.individualJobExperienceService.getByIndividual(individual.getId()).getData();
        cvDto.setIndividualJobExperiences(individualJobExperiences);

        var individualLanguages = this.individualLanguageService.getByIndividual(individual.getId()).getData();
        cvDto.setIndividualLanguages(individualLanguages);
    
        return new SuccessDataResult<CvDto>(cvDto);
    }

    // private Individual individual;
    // private UserPhoto userPhoto;
    // private UserBiography userBiography;
    // private LinkedinAccount linkedinAccount;
    // private GithubAccount githubAccount;
    // private List<IndividualTechnology> individualTechnologies;
    // private List<IndividualEducation> individualEducations;
    // private List<IndividualJobExperience> individualJobExperiences;
    // private List<IndividualLanguage> individualLanguages;

    @Override
    public Result add(CvDto cvDto) {

        var individual = cvDto.getIndividual();

        // var userPhoto = cvDto.getUserPhoto();
        // userPhoto.setUser(individual.getUser());
        // this.userPhotoService.add(userPhoto); 

        var biography = cvDto.getUserBiography();
        biography.setUser(individual.getUser());
        this.userBiographyService.add(biography);

        var githubAccount = cvDto.getGithubAccount();
        githubAccount.setUser(individual.getUser());
        this.githubAccountService.add(githubAccount);

        var linkedinAccount = cvDto.getLinkedinAccount();
        linkedinAccount.setUser(individual.getUser());
        this.linkedinAccountService.add(linkedinAccount);

        for (var individualTechnology : cvDto.getIndividualTechnologies()) {
            individualTechnology.setIndividual(individual);
            this.individualTechnologyService.add(individualTechnology);
        }

        for (var individualEducation : cvDto.getIndividualEducations()) {
            individualEducation.setIndividual(individual);
            this.individualEducationService.add(individualEducation);
        }

        for (var individualJobExperience : cvDto.getIndividualJobExperiences()) {
            individualJobExperience.setIndividual(individual);
            this.individualJobExperienceService.add(individualJobExperience);
        }

        for (var individualLanguage : cvDto.getIndividualLanguages()) {
            individualLanguage.setIndividual(individual);
            this.individualLanguageService.add(individualLanguage);
        }
        return new SuccessResult();
    }

    @Override
    public Result update(CvDto cvDto) {
        return null;
    }

    @Override
    public Result delete(CvDto cvDto) {
        
        this.userPhotoService.delete(cvDto.getUserPhoto());
        this.userBiographyService.delete(cvDto.getUserBiography().getId());
        this.githubAccountService.delete(cvDto.getGithubAccount().getId());
        this.linkedinAccountService.delete(cvDto.getLinkedinAccount().getId());
        
        for(var individualTechnology : cvDto.getIndividualTechnologies()) {
            this.individualTechnologyService.delete(individualTechnology.getId());
        }

        for (var individualEducation : cvDto.getIndividualEducations()) {
            this.individualEducationService.delete(individualEducation.getId());
        }

        for (var individualJobExperience : cvDto.getIndividualJobExperiences()) {
            this.individualJobExperienceService.delete(individualJobExperience.getId());
        }

        for (var individualLanguage : cvDto.getIndividualLanguages()) {
            this.individualLanguageService.delete(individualLanguage.getId());   
        }

        return new SuccessResult();
    }

}