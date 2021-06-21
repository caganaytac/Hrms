package hrms.api.controllers;

import hrms.business.abstracts.SchoolFacultyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.SchoolFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schoolFaculties")
public class SchoolFacultiesController {
    private SchoolFacultyService schoolFacultyService;

    @Autowired
    public SchoolFacultiesController(SchoolFacultyService schoolFacultyService) {
        this.schoolFacultyService = schoolFacultyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<SchoolFaculty>>> getAll() {
        var result = this.schoolFacultyService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<SchoolFaculty>> getById(@RequestParam int id) {
        var result = this.schoolFacultyService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<SchoolFaculty>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<SchoolFaculty>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getBySchool")
    public ResponseEntity<DataResult<List<SchoolFaculty>>> getBySchool(@RequestParam Integer id) {
        var result = this.schoolFacultyService.getBySchool(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody SchoolFaculty schoolFaculty) {
        var result = this.schoolFacultyService.add(schoolFaculty);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody SchoolFaculty schoolFaculty) {
        var result = this.schoolFacultyService.update(schoolFaculty);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody SchoolFaculty schoolFaculty) {
        var result = this.schoolFacultyService.delete(schoolFaculty);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}
