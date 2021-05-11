package hrms.api.controllers;

import hrms.business.abstracts.IndividualJobPositionService;
import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/individualjobpositions")
public class IndividualJobPositionsController {
    private IndividualJobPositionService individualJobPositionService;

    @Autowired
    public IndividualJobPositionsController(IndividualJobPositionService individualJobPositionService) {
        this.individualJobPositionService = individualJobPositionService;
    }

    @GetMapping("/getall")
    public List<IndividualJobPosition> getAll() {
        return this.individualJobPositionService.getAll();
    }

}