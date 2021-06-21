package hrms.api.controllers;

import hrms.business.abstracts.SchoolService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolsController {
    private SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<School>>> getAll() {
        var result = this.schoolService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<School>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<School>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<School>> getById(@RequestParam int id) {
        var result = this.schoolService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<School>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<School>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody School school) {
        var result = this.schoolService.add(school);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody School school) {
        var result = this.schoolService.update(school);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody School school) {
        var result = this.schoolService.delete(school);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}
