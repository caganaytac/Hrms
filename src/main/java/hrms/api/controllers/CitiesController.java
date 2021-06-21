package hrms.api.controllers;

import hrms.business.abstracts.CityService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<City>>> getAll() {
        var result = this.cityService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<City>>>(result,HttpStatus.OK);
        return new ResponseEntity<DataResult<List<City>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<City>> getById(@RequestParam short id) {
        var result = this.cityService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<City>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<City>>(result, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody City city) {
        var result = this.cityService.add(city);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody City city) {
        var result = this.cityService.update(city);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody City city) {
        var result = this.cityService.delete(city);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}