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

import hrms.business.abstracts.WorkAreaService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.WorkArea;

@RestController
@RequestMapping("/api/workAreas")
public class WorkAreasController {
    private WorkAreaService workAreaService;

    @Autowired
    public WorkAreasController(WorkAreaService workAreaService){
        this.workAreaService = workAreaService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<WorkArea>>> getAll() {
        var result = this.workAreaService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<WorkArea>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<WorkArea>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<WorkArea>> getById(@RequestParam short id) {
        var result = this.workAreaService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<WorkArea>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<WorkArea>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody WorkArea workArea) {
        var result = this.workAreaService.add(workArea);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody WorkArea workArea) {
        var result = this.workAreaService.update(workArea);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody WorkArea workArea) {
        var result = this.workAreaService.delete(workArea);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}