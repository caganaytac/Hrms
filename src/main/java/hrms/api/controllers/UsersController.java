package hrms.api.controllers;

import hrms.business.abstracts.UserService;
import hrms.core.utilities.results.DataResult;
import hrms.core.entities.User;
import hrms.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<User>>> getAll() {
        var result = this.userService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<User>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<User>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<User>> getById(@RequestParam int id) {
        var result = this.userService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<User>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<User>>(result, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody User user) {
        var result = this.userService.add(user);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody User user) {
        var result = this.userService.update(user);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody User user) {
        var result = this.userService.delete(user);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}