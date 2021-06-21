package hrms.api.controllers;

import hrms.business.abstracts.StatusService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusesController {
    private StatusService statusService;

    @Autowired
    public StatusesController(StatusService statusService){
        this.statusService = statusService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Status>>> getAll() {
        var result = this.statusService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Status>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Status>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<Status>> getById(@RequestParam short id) {
        var result = this.statusService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<Status>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Status>>(result, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Status educationStatus) {
        var result = this.statusService.add(educationStatus);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Status educationStatus) {
        var result = this.statusService.update(educationStatus);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody Status educationStatus) {
        var result = this.statusService.delete(educationStatus);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}