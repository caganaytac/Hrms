package hrms.api.controllers;

import hrms.business.abstracts.CorporateService;
import hrms.entities.concretes.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/corporates")
public class CorporatesController {
    private CorporateService corporateService;

    @Autowired
    public CorporatesController(CorporateService corporateService) {
        this.corporateService = corporateService;
    }

    @GetMapping("getall")
    public List<Corporate> getAll() {
        return this.corporateService.getAll();
    }
}