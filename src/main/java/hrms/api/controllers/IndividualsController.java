package hrms.api.controllers;

import hrms.business.abstracts.IndividualService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.Individual;
import hrms.entities.dtos.CvDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individuals")
public class IndividualsController extends BaseController<IndividualService, Individual, Integer> {
    private final IndividualService individualService;

    @Autowired
    public IndividualsController(IndividualService individualService) {
        super(individualService);
        this.individualService = individualService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<Individual>> getByUser(Integer id) {
        DataResult<Individual> result = this.individualService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<Individual>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Individual>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCv")
    public ResponseEntity<DataResult<List<CvDto>>> getCv(@RequestParam Integer id) {
        DataResult<List<CvDto>> result = this.individualService.getCv(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<CvDto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<CvDto>>>(result, HttpStatus.BAD_REQUEST);
    }
}