package hrms.api.controllers;

import hrms.business.abstracts.LanguageService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.Language;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController extends BaseController<LanguageService, Language, Short>{
    public LanguagesController(LanguageService languageService) {
        super(languageService);
    }
}