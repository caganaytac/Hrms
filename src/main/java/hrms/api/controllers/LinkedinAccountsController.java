package hrms.api.controllers;

import hrms.business.abstracts.LinkedinAccountService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.LinkedinAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linkedinAccounts")
public class LinkedinAccountsController {
    private LinkedinAccountService linkedinAccountService;

    @Autowired
    public LinkedinAccountsController(LinkedinAccountService linkedinAccountService) {
        this.linkedinAccountService = linkedinAccountService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<LinkedinAccount>>> getAll() {
        var result = this.linkedinAccountService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<LinkedinAccount>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<LinkedinAccount>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<LinkedinAccount>> getById(@RequestParam int id) {
        var result = this.linkedinAccountService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<LinkedinAccount>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<LinkedinAccount>>(result, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody LinkedinAccount linkedinAccount) {
        var result = this.linkedinAccountService.add(linkedinAccount);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody LinkedinAccount linkedinAccount) {
        var result = this.linkedinAccountService.update(linkedinAccount);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody LinkedinAccount linkedinAccount) {
        var result = this.linkedinAccountService.delete(linkedinAccount);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}