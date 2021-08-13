package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.IndividualJobExperienceService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualJobExperience;

@RestController
@RequestMapping("/api/individualJobExperiences")
public class IndividualJobExperiencesController
        extends BaseController<IndividualJobExperienceService, IndividualJobExperience, Long> {
    private IndividualJobExperienceService individualJobExperienceService;

    @Autowired
    public IndividualJobExperiencesController(IndividualJobExperienceService individualJobExperienceService) {
        super(individualJobExperienceService);
        this.individualJobExperienceService = individualJobExperienceService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<List<IndividualJobExperience>>> getByIndividual(@RequestParam Integer id) {
        DataResult<List<IndividualJobExperience>> result = this.individualJobExperienceService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<IndividualJobExperience>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobExperience>>>(result, HttpStatus.BAD_REQUEST);
    }
}