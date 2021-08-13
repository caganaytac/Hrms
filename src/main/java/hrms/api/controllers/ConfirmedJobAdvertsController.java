package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.ConfirmedJobAdvert;
import hrms.business.abstracts.ConfirmedJobAdvertService;

@RestController
@RequestMapping("/api/confirmedJobAdverts")
public class ConfirmedJobAdvertsController extends BaseController<ConfirmedJobAdvertService, ConfirmedJobAdvert, Long> {
    private final ConfirmedJobAdvertService confirmedJobAdvertService;

    @Autowired
    public ConfirmedJobAdvertsController(ConfirmedJobAdvertService confirmedJobAdvertService) {
        super(confirmedJobAdvertService);
        this.confirmedJobAdvertService = confirmedJobAdvertService;
    }

    @GetMapping("/getByJobAdvert")
    public ResponseEntity<DataResult<ConfirmedJobAdvert>> getByJobAdvert(@RequestParam Long id) {
        DataResult<ConfirmedJobAdvert> result = this.confirmedJobAdvertService.getByJobAdvert(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<ConfirmedJobAdvert>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<ConfirmedJobAdvert>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByEmployee")
    public ResponseEntity<DataResult<List<ConfirmedJobAdvert>>> getByEmployee(@RequestParam Integer id) {
        DataResult<List<ConfirmedJobAdvert>> result = this.confirmedJobAdvertService.getByEmployee(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<ConfirmedJobAdvert>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<ConfirmedJobAdvert>>>(result, HttpStatus.BAD_REQUEST);
    }
}