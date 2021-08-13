package hrms.api.controllers;

import hrms.business.abstracts.SchoolFacultyService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.SchoolFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schoolFaculties")
public class SchoolFacultiesController extends BaseController<SchoolFacultyService, SchoolFaculty, Integer>{
    private final SchoolFacultyService schoolFacultyService;

    @Autowired
    public SchoolFacultiesController(SchoolFacultyService schoolFacultyService) {
        super(schoolFacultyService);
        this.schoolFacultyService = schoolFacultyService;
    }

    @GetMapping("/getBySchool")
    public ResponseEntity<DataResult<List<SchoolFaculty>>> getBySchool(@RequestParam Integer id) {
        DataResult<List<SchoolFaculty>> result = this.schoolFacultyService.getBySchool(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/getByFaculty")
    public ResponseEntity<DataResult<List<SchoolFaculty>>> getByFaculty(@RequestParam Short id) {
        DataResult<List<SchoolFaculty>> result = this.schoolFacultyService.getByFaculty(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<SchoolFaculty>>>(result, HttpStatus.BAD_REQUEST);
    }
}
