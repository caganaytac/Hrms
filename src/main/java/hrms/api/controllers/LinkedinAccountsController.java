package hrms.api.controllers;

import hrms.business.abstracts.LinkedinAccountService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.LinkedinAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/linkedinAccounts")
public class LinkedinAccountsController extends BaseController<LinkedinAccountService, LinkedinAccount, Integer> {
    private final LinkedinAccountService linkedinAccountService;

    @Autowired
    public LinkedinAccountsController(LinkedinAccountService linkedinAccountService) {
        super(linkedinAccountService);
        this.linkedinAccountService = linkedinAccountService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<LinkedinAccount>> getByUser(Integer id) {
        DataResult<LinkedinAccount> result = this.linkedinAccountService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<LinkedinAccount>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<LinkedinAccount>>(result, HttpStatus.BAD_REQUEST);
    }
}