package hrms.api.controllers;

import hrms.business.abstracts.UserBiographyService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.UserBiography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userBiographies")
public class UserBiographiesController extends BaseController<UserBiographyService, UserBiography, Long> {
    private final UserBiographyService userBiographyService;

    @Autowired
    public UserBiographiesController(UserBiographyService userBiographyService) {
        super(userBiographyService);
        this.userBiographyService = userBiographyService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<UserBiography>> getByUser(@RequestParam Integer id) {
        DataResult<UserBiography> result = this.userBiographyService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<UserBiography>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<UserBiography>>(result, HttpStatus.BAD_REQUEST);
    }
}