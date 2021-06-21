package hrms.api.controllers;

import hrms.business.abstracts.CorporateService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporates")
public class CorporatesController {
    private CorporateService corporateService;

    @Autowired
    public CorporatesController(CorporateService corporateService) {
        this.corporateService = corporateService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Corporate>>> getAll() {
        var result = this.corporateService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Corporate>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Corporate>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<Corporate>> getById(@RequestParam int id) {
        var result = this.corporateService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<Corporate>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Corporate>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<DataResult<List<Corporate>>> getAllByUser(@RequestParam int id) {
        var result = this.corporateService.getAllByUser(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Corporate>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Corporate>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllByCompanyName")
    public ResponseEntity<DataResult<List<Corporate>>> getAllByCompanyName(@RequestParam String companyName) {
        var result = this.corporateService.getAllByCompanyName(companyName);
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Corporate>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Corporate>>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Corporate corporate) {
        var result = this.corporateService.add(corporate);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Corporate corporate) {
        var result = this.corporateService.update(corporate);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody Corporate corporate) {
        var result = this.corporateService.delete(corporate);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}