package hrms.api.controllers;

import hrms.business.abstracts.IndividualJobPositionService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualJobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualJobPositions")
public class IndividualJobPositionsController
        extends BaseController<IndividualJobPositionService, IndividualJobPosition, Long> {
    private IndividualJobPositionService individualJobPositionService;

    @Autowired
    public IndividualJobPositionsController(IndividualJobPositionService individualJobPositionService) {
        super(individualJobPositionService);
        this.individualJobPositionService = individualJobPositionService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<List<IndividualJobPosition>>> getByIndividual(@RequestParam Integer id) {
        DataResult<List<IndividualJobPosition>> result = this.individualJobPositionService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByJobPosition")
    public ResponseEntity<DataResult<List<IndividualJobPosition>>> getByJobPosition(@RequestParam Short id) {
        DataResult<List<IndividualJobPosition>> result = this.individualJobPositionService.getByJobPosition(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualJobPosition>>>(result, HttpStatus.BAD_REQUEST);
    }
}