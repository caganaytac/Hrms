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

import hrms.business.abstracts.WorkTimeService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.WorkTime;

@RestController
@RequestMapping("/api/workTimes")
public class WorkTimesController {
    private WorkTimeService workTimeService;

    @Autowired
    public WorkTimesController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<WorkTime>>> getAll() {
        var result = this.workTimeService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<WorkTime>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<WorkTime>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<WorkTime>> getById(@RequestParam short id) {
        var result = this.workTimeService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<WorkTime>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<WorkTime>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody WorkTime workTime) {
        var result = this.workTimeService.add(workTime);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody WorkTime workTime) {
        var result = this.workTimeService.update(workTime);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody WorkTime workTime) {
        var result = this.workTimeService.delete(workTime);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}