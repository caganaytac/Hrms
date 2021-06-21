package hrms.api.controllers;

import hrms.business.abstracts.IndividualLanguageService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.IndividualLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualLanguages")
public class IndividualLanguagesController {
    private IndividualLanguageService individualLanguageService;

    @Autowired
    public IndividualLanguagesController(IndividualLanguageService individualLanguageService) {
        this.individualLanguageService = individualLanguageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<IndividualLanguage>>> getAll() {
        var result = this.individualLanguageService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<IndividualLanguage>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualLanguage>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<IndividualLanguage>> getById(@RequestParam int id) {
        var result = this.individualLanguageService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<IndividualLanguage>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<IndividualLanguage>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody IndividualLanguage individualLanguage) {
        var result = this.individualLanguageService.add(individualLanguage);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody IndividualLanguage individualLanguage) {
        var result = this.individualLanguageService.update(individualLanguage);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody IndividualLanguage individualLanguage) {
        var result = this.individualLanguageService.delete(individualLanguage);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}