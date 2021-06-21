package hrms.api.controllers;

import hrms.business.abstracts.IndividualJobPositionService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualJobPositions")
public class IndividualJobPositionsController {
    private IndividualJobPositionService individualJobPositionService;

    @Autowired
    public IndividualJobPositionsController(IndividualJobPositionService individualJobPositionService) {
        this.individualJobPositionService = individualJobPositionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<IndividualJobPosition>>> getAll() {
        var result = this.individualJobPositionService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<IndividualJobPosition>> getById(@RequestParam long id) {
        var result = this.individualJobPositionService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<IndividualJobPosition>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<IndividualJobPosition>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByIndividual")
    public ResponseEntity<DataResult<List<IndividualJobPosition>>> getAllByIndividual(@RequestParam int id) {
        var result = this.individualJobPositionService.getAllByIndividual(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByJobPosition")
    public ResponseEntity<DataResult<List<IndividualJobPosition>>> getAllByJobPosition(@RequestParam int id) {
        var result = this.individualJobPositionService.getAllByJobPosition(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody IndividualJobPosition individualJobPosition) {
        var result = this.individualJobPositionService.add(individualJobPosition);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody IndividualJobPosition individualJobPosition) {
        var result = this.individualJobPositionService.update(individualJobPosition);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody IndividualJobPosition individualJobPosition) {
        var result = this.individualJobPositionService.delete(individualJobPosition);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}