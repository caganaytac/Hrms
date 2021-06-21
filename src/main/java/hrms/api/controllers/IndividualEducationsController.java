package hrms.api.controllers;

import hrms.business.abstracts.IndividualEducationService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualEducation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualEducations")
public class IndividualEducationsController {
    private IndividualEducationService individualEducationService;

    @Autowired
    public IndividualEducationsController(IndividualEducationService individualEducationService) {
        this.individualEducationService = individualEducationService;
    }
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<IndividualEducation>>> getAll() {
        var result = this.individualEducationService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualEducation>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualEducation>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<IndividualEducation>> getById(@RequestParam long id) {
        var result = this.individualEducationService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<IndividualEducation>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<IndividualEducation>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody IndividualEducation individualEducation) {
        var result = this.individualEducationService.add(individualEducation);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody IndividualEducation individualEducation) {
        var result = this.individualEducationService.update(individualEducation);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody IndividualEducation individualEducation) {
        var result = this.individualEducationService.delete(individualEducation);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}