package hrms.api.controllers;

import hrms.business.abstracts.JobPositionService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPositions")
public class JobPositionsController {
    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<JobPosition>>> getAll() {
        var result = this.jobPositionService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<JobPosition>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<JobPosition>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<JobPosition>> getById(@RequestParam short id) {
        var result = this.jobPositionService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<JobPosition>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<JobPosition>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody JobPosition jobPosition) {
        var result = this.jobPositionService.add(jobPosition);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody JobPosition jobPosition) {
        var result = this.jobPositionService.update(jobPosition);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody JobPosition jobPosition) {
        var result = this.jobPositionService.delete(jobPosition);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

}