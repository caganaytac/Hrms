package hrms.api.controllers;

import hrms.business.abstracts.IndividualEducationService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualEducation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualEducations")
public class IndividualEducationsController
        extends BaseController<IndividualEducationService, IndividualEducation, Long> {
    private final IndividualEducationService individualEducationService;

    @Autowired
    public IndividualEducationsController(IndividualEducationService individualEducationService) {
        super(individualEducationService);
        this.individualEducationService = individualEducationService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<List<IndividualEducation>>> getByIndividual(Integer id) {
        DataResult<List<IndividualEducation>> result = this.individualEducationService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<IndividualEducation>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualEducation>>>(result, HttpStatus.BAD_REQUEST);
    }
}