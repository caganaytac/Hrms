package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class JobAdvertsController extends BaseController<JobAdvertService, JobAdvert, Long> {
    private final JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertsController(JobAdvertService jobAdvertService) {
        super(jobAdvertService);
        this.jobAdvertService = jobAdvertService;
    }

    @GetMapping("/getDetails")
    public ResponseEntity<DataResult<List<JobAdvertDto>>> getDetails() {
        DataResult<List<JobAdvertDto>> result = this.jobAdvertService.getDetails();
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<JobAdvertDto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<JobAdvertDto>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDetailsAsPageable")
    public ResponseEntity<DataResult<Page<JobAdvertDto>>> getDetails(
            @RequestParam(defaultValue = "0") Integer number, @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "createDate") String[] sortProperties,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        DataResult<Page<JobAdvertDto>> result = this.jobAdvertService.getDetails(number, size, sortProperties,
                sortDirection);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<Page<JobAdvertDto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Page<JobAdvertDto>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDetailById")
    public ResponseEntity<DataResult<JobAdvertDto>> getDetailById(Long id) {
        DataResult<JobAdvertDto> result = this.jobAdvertService.getDetailById(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<JobAdvertDto>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<JobAdvertDto>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDetailsByCorporate")
    public ResponseEntity<DataResult<List<JobAdvertDto>>> getByCorporate(@RequestParam Integer id) {
        DataResult<List<JobAdvertDto>> result = this.jobAdvertService.getDetailsByCorporate(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<JobAdvertDto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<JobAdvertDto>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDetailsByCorporateAsPageable")
    public ResponseEntity<DataResult<Page<JobAdvertDto>>> getDetailsAsPageable(@RequestParam Integer id,
            @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "createDate") String[] sortProperties,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        DataResult<Page<JobAdvertDto>> result = this.jobAdvertService.getDetailsByCorporate(id, pageNo, pageSize, sortProperties,
                sortDirection);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<Page<JobAdvertDto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Page<JobAdvertDto>>>(result, HttpStatus.BAD_REQUEST);
    }
}