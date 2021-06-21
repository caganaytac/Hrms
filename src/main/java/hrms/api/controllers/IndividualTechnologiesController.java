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

import hrms.business.abstracts.IndividualTechnologyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualTechnology;

@RestController
@RequestMapping("/api/individualTechnologies")
public class IndividualTechnologiesController {
    private IndividualTechnologyService individualTechnologyService;

    @Autowired
    public IndividualTechnologiesController(IndividualTechnologyService individualTechnologyService) {
        this.individualTechnologyService = individualTechnologyService;
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<IndividualTechnology>>> getAll() {
        var result = this.individualTechnologyService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualTechnology>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualTechnology>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<IndividualTechnology>> getById(@RequestParam long id) {
        var result = this.individualTechnologyService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<IndividualTechnology>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<IndividualTechnology>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByIndividual")
    public ResponseEntity<DataResult<List<IndividualTechnology>>> getAllByIndividual(@RequestParam int id) {
        var result = this.individualTechnologyService.getAllByIndividual(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualTechnology>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualTechnology>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody IndividualTechnology individualTechnology) {
        var result = this.individualTechnologyService.add(individualTechnology);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody IndividualTechnology individualTechnology) {
        var result = this.individualTechnologyService.update(individualTechnology);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody IndividualTechnology individualTechnology) {
        var result = this.individualTechnologyService.delete(individualTechnology);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus .BAD_REQUEST);
    }
}