package hrms.api.controllers;

import hrms.business.abstracts.UserBiographyService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.UserBiography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userBiographies")
public class UserBiographiesController {
    private UserBiographyService userBiographyService;

    @Autowired
    public UserBiographiesController(UserBiographyService userBiographyService) {
        this.userBiographyService = userBiographyService;
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<UserBiography>>> getAll() {
        var result = this.userBiographyService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<UserBiography>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<UserBiography>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<UserBiography>> getById(@RequestParam long id) {
        var result = this.userBiographyService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<UserBiography>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<UserBiography>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<UserBiography>> getByUser(@RequestParam int id) {
        var result = this.userBiographyService.getByUser(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<UserBiography>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<UserBiography>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody UserBiography userBiography) {
        var result = this.userBiographyService.add(userBiography);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody UserBiography userBiography) {
        var result = this.userBiographyService.update(userBiography);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody UserBiography userBiography) {
        var result = this.userBiographyService.delete(userBiography);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}