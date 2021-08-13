package hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;

import hrms.entities.concretes.ConfirmedCorporate;

import hrms.business.abstracts.ConfirmedCorporateService;

@RestController
@RequestMapping("/api/confirmedCorporates")
public class ConfirmedCorporatesController {
    private final ConfirmedCorporateService confirmedCorporateService;

    @Autowired
    public ConfirmedCorporatesController(ConfirmedCorporateService confirmedCorporateService) {
        this.confirmedCorporateService = confirmedCorporateService;
    }

    @GetMapping("getAll")
    public ResponseEntity<DataResult<List<ConfirmedCorporate>>> getAll() {
        DataResult<List<ConfirmedCorporate>> result = this.confirmedCorporateService.getAll();
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<ConfirmedCorporate>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<ConfirmedCorporate>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getById")
    public ResponseEntity<DataResult<ConfirmedCorporate>> getById(@RequestParam Long id) {
        DataResult<ConfirmedCorporate> result = this.confirmedCorporateService.getById(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<ConfirmedCorporate>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<ConfirmedCorporate>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getByCorporate")
    public ResponseEntity<DataResult<List<ConfirmedCorporate>>> getByCorporate(@RequestParam Integer id) {
        DataResult<List<ConfirmedCorporate>> result = this.confirmedCorporateService.getByCorporate(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<ConfirmedCorporate>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<ConfirmedCorporate>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getByEmployee")
    public ResponseEntity<DataResult<List<ConfirmedCorporate>>> getByEmployee(@RequestParam Integer id) {
        DataResult<List<ConfirmedCorporate>> result = this.confirmedCorporateService.getByEmployee(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<ConfirmedCorporate>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<ConfirmedCorporate>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid ConfirmedCorporate confirmedCorporate) {
        Result result = this.confirmedCorporateService.add(confirmedCorporate);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}