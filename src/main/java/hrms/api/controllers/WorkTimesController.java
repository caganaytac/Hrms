package hrms.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.WorkTimeService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.WorkTime;

@RestController
@RequestMapping("/api/workTimes")
public class WorkTimesController extends BaseController<WorkTimeService, WorkTime, Short>{
    public WorkTimesController(WorkTimeService workTimeService) {
        super(workTimeService);
    }
}