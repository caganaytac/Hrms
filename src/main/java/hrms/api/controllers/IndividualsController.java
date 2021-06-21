package hrms.api.controllers;

import hrms.business.abstracts.IndividualService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Individual;
import hrms.entities.dtos.CvDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individuals")
public class IndividualsController {
    private IndividualService individualService;

    @Autowired
    public IndividualsController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Individual>>> getAll() {
        var result = this.individualService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Individual>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Individual>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<Individual>> getById(@RequestParam int id) {
        var result = this.individualService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<Individual>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Individual>>(result, HttpStatus.BAD_REQUEST);
    }

    // @GetMapping("/getCv")
    // public ResponseEntity<DataResult<CvDto>> getCv(@RequestParam int id) {
    //     var result = this.individualService.getCv(id);
    //     if (result.isSuccess()) return new ResponseEntity<DataResult<CvDto>>(result, HttpStatus.OK);
    //     return new ResponseEntity<DataResult<CvDto>>(result, HttpStatus.BAD_REQUEST);
    // }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Individual individual) {
        var result = this.individualService.add(individual);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Individual individual) {
        var result = this.individualService.update(individual);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody Individual individual) {
        var result = this.individualService.delete(individual);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}