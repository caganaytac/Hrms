package hrms.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.WorkAreaService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.WorkArea;

@RestController
@RequestMapping("/api/workAreas")
public class WorkAreasController extends BaseController<WorkAreaService, WorkArea, Short> {
    public WorkAreasController(WorkAreaService workAreaService) {
        super(workAreaService);
    }
}