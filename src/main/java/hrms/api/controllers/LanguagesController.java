package hrms.api.controllers;

import hrms.business.abstracts.LanguageService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.Language;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private LanguageService languageService;

    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Language>>> getAll() {
        var result = this.languageService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<Language>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<Language>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<Language>> getById(@RequestParam short id) {
        var result = this.languageService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<Language>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Language>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Language language) {
        var result = this.languageService.add(language);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Language language) {
        var result = this.languageService.update(language);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody Language language) {
        var result = this.languageService.delete(language);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}