package hrms.api.controllers;

import hrms.business.abstracts.FacultyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
public class FacultiesController {
    private FacultyService facultyService;

    @Autowired
    public FacultiesController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Faculty>>> getAll() {
        var result = this.facultyService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Faculty>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Faculty>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<Faculty>> getById(@RequestParam short id) {
        var result = this.facultyService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<Faculty>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Faculty>>(result, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Faculty faculty) {
        var result = this.facultyService.add(faculty);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Faculty faculty) {
        var result = this.facultyService.update(faculty);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody Faculty faculty) {
        var result = this.facultyService.delete(faculty);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}