package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.IndividualJobExperienceService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualJobExperience;

@RestController
@RequestMapping("/api/individualJobExperiences")
public class IndividualJobExperiencesController {
    private IndividualJobExperienceService individualJobExperienceService;

    @Autowired
    public IndividualJobExperiencesController(IndividualJobExperienceService individualJobExperienceService) {
        this.individualJobExperienceService = individualJobExperienceService;
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<IndividualJobExperience>>> getAll() {
        var result = this.individualJobExperienceService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualJobExperience>>>(result,HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobExperience>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<IndividualJobExperience>> getById(@RequestParam long id) {
        var result = this.individualJobExperienceService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<IndividualJobExperience>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<IndividualJobExperience>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByIndividual")
    public ResponseEntity<DataResult<List<IndividualJobExperience>>> getAllByIndividual(@RequestParam int id) {
        var result = this.individualJobExperienceService.getAllByIndividual(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualJobExperience>>>(result,HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobExperience>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody IndividualJobExperience individualJobExperience) {
        var result = this.individualJobExperienceService.add(individualJobExperience);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody IndividualJobExperience individualJobExperience) {
        var result = this.individualJobExperienceService.update(individualJobExperience);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody IndividualJobExperience individualJobExperience) {
        var result = this.individualJobExperienceService.delete(individualJobExperience);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}