package hrms.api.controllers;

import hrms.business.abstracts.CorporateService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/corporates")
public class CorporatesController extends BaseController<CorporateService, Corporate, Integer> {
    private final CorporateService corporateService;

    @Autowired
    public CorporatesController(CorporateService corporateService) {
        super(corporateService);
        this.corporateService = corporateService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<Corporate>> getAllByUser(@RequestParam Integer id) {
        DataResult<Corporate> result = this.corporateService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<Corporate>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<Corporate>>(result, HttpStatus.BAD_REQUEST);
    }
}