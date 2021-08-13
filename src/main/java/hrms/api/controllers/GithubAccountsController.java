package hrms.api.controllers;

import hrms.business.abstracts.GithubAccountService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.GithubAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/githubAccounts")
public class GithubAccountsController extends BaseController<GithubAccountService, GithubAccount, Integer>{
    private final GithubAccountService githubAccountService;

    @Autowired
    public GithubAccountsController(GithubAccountService githubAccountService) {
        super(githubAccountService);
        this.githubAccountService = githubAccountService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<GithubAccount>> getByUser(Integer id) {
        DataResult<GithubAccount> result = this.githubAccountService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<GithubAccount>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<GithubAccount>>(result, HttpStatus.BAD_REQUEST);
    }
}