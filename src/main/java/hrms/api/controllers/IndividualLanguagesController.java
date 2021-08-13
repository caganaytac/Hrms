package hrms.api.controllers;

import hrms.business.abstracts.IndividualLanguageService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.IndividualLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualLanguages")
public class IndividualLanguagesController extends BaseController<IndividualLanguageService, IndividualLanguage, Long> {
    private final IndividualLanguageService individualLanguageService;

    @Autowired
    public IndividualLanguagesController(IndividualLanguageService individualLanguageService) {
        super(individualLanguageService);
        this.individualLanguageService = individualLanguageService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<List<IndividualLanguage>>> getByIndividual(Integer id) {
        DataResult<List<IndividualLanguage>> result = this.individualLanguageService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<IndividualLanguage>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<IndividualLanguage>>>(result, HttpStatus.BAD_REQUEST);
    }
}