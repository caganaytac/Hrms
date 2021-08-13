package hrms.api.controllers;

import hrms.business.abstracts.SchoolService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.School;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schools")
public class SchoolsController extends BaseController<SchoolService, School, Integer>{
    public SchoolsController(SchoolService schoolService) {
        super(schoolService);
    }
}