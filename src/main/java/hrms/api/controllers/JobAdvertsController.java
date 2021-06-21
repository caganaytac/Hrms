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

import hrms.business.abstracts.JobAdvertService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.JobAdvert;

@RestController
@RequestMapping("/api/jobAdverts")
public class JobAdvertsController {
    private JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertsController(JobAdvertService jobAdvertService) {
        this.jobAdvertService = jobAdvertService;
    }
      
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<JobAdvert>>> getAll() {
        var result = this.jobAdvertService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<JobAdvert>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<JobAdvert>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<JobAdvert>> getById(@RequestParam long id) {
        var result = this.jobAdvertService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<JobAdvert>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<JobAdvert>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByCorporate")
    public ResponseEntity<DataResult<List<JobAdvert>>> getAllByCorporate(@RequestParam Integer id) {
        var result = this.jobAdvertService.getAllByCorporate(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<JobAdvert>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<JobAdvert>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody JobAdvert jobAdvert) {
        var result = this.jobAdvertService.add(jobAdvert);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody JobAdvert jobAdvert) {
        var result = this.jobAdvertService.update(jobAdvert);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody JobAdvert jobAdvert) {
        var result = this.jobAdvertService.delete(jobAdvert);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus .BAD_REQUEST);
    }
}