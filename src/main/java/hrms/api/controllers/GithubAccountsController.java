package hrms.api.controllers;

import hrms.business.abstracts.GithubAccountService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.GithubAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/githubAccounts")
public class GithubAccountsController {
    private GithubAccountService githubAccountService;

    @Autowired
    public GithubAccountsController(GithubAccountService githubAccountService) {
        this.githubAccountService = githubAccountService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<GithubAccount>>> getAll() {
        var result = this.githubAccountService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<GithubAccount>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<GithubAccount>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<GithubAccount>> getById(@RequestParam int id) {
        var result = this.githubAccountService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<GithubAccount>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<GithubAccount>>(result, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody GithubAccount githubAccount) {
        var result = this.githubAccountService.add(githubAccount);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody GithubAccount githubAccount) {
        var result = this.githubAccountService.update(githubAccount);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody GithubAccount githubAccount) {
        var result = this.githubAccountService.delete(githubAccount);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}