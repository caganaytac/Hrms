package hrms.api.controllers;

import hrms.business.abstracts.StatusService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.Status;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statuses")
public class StatusesController extends BaseController<StatusService, Status, Short>{
    public StatusesController(StatusService statusService) {
        super(statusService);
    }
}