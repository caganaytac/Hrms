package hrms.api.controllers;

import hrms.business.abstracts.JobPositionService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.JobPosition;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobPositions")
public class JobPositionsController extends BaseController<JobPositionService, JobPosition, Short> {
    public JobPositionsController(JobPositionService jobPositionService) {
        super(jobPositionService);
    }
}