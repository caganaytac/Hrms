package hrms.api.controllers;

import hrms.business.abstracts.FacultyService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.Faculty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculties")
public class FacultiesController extends BaseController<FacultyService, Faculty, Short>{
    public FacultiesController(FacultyService facultyService) {
        super(facultyService);
    }
}