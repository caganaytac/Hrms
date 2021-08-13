package hrms.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.TechnologyService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.Technology;

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController extends BaseController<TechnologyService, Technology, Short> {
    public TechnologiesController(TechnologyService technologyService) {
        super(technologyService);
    }
}