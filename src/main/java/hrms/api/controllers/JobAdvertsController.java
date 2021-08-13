package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobAdvertService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.JobAdvert;
import hrms.entities.dtos.JobAdvertDto;

@RestController
@RequestMapping("/api/jobAdverts")
public class JobAdvertsController extends BaseController<JobAdvertService, JobAdvert, Long>{
    private final JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertsController(JobAdvertService jobAdvertService) {
        super(jobAdvertService);
        this.jobAdvertService = jobAdvertService;
    }

    @GetMapping("/getByCorporate")
    public ResponseEntity<DataResult<List<JobAdvertDto>>> getByCorporate(@RequestParam Integer id) {
        DataResult<List<JobAdvertDto>> result = this.jobAdvertService.getDetailsByCorporate(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<JobAdvertDto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<JobAdvertDto>>>(result, HttpStatus.BAD_REQUEST);
    }
}