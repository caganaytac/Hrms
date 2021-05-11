package hrms.api.controllers;

import hrms.business.abstracts.IndividualService;
import hrms.entities.concretes.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/individuals")
public class IndividualsController {
    private IndividualService individualService;

    @Autowired
    public IndividualsController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/getall")
    public List<Individual> getAll() {
        return this.individualService.getAll();
    }
}
