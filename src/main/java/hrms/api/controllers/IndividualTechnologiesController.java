package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.IndividualTechnologyService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualTechnology;

@RestController
@RequestMapping("/api/individualTechnologies")
public class IndividualTechnologiesController
        extends BaseController<IndividualTechnologyService, IndividualTechnology, Long> {
    private IndividualTechnologyService individualTechnologyService;

    @Autowired
    public IndividualTechnologiesController(IndividualTechnologyService individualTechnologyService) {
        super(individualTechnologyService);
        this.individualTechnologyService = individualTechnologyService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<List<IndividualTechnology>>> getByIndividual(@RequestParam Integer id) {
        DataResult<List<IndividualTechnology>> result = this.individualTechnologyService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<IndividualTechnology>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualTechnology>>>(result, HttpStatus.BAD_REQUEST);
    }
}