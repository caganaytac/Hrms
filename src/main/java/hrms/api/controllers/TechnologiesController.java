package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.TechnologyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Technology;

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {
    private TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Technology>>> getAll() {
        var result = this.technologyService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Technology>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Technology>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<Technology>> getById(@RequestParam Short id) {
        var result = this.technologyService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<Technology>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Technology>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Technology technology) {
        var result = this.technologyService.add(technology);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Technology technology) {
        var result = this.technologyService.update(technology);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody Technology technology) {
        var result = this.technologyService.delete(technology);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }    
}