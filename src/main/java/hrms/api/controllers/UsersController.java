package hrms.api.controllers;

import hrms.business.abstracts.UserService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController extends BaseController<UserService, User, Integer>{
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/confirm")
    public ResponseEntity<Result> confirm(@RequestParam Integer id) {
        Result result = this.userService.confirm(id);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}