package hrms.api.controllers;

import hrms.business.abstracts.PhoneNumberService;
import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phoneNumbers")
public class PhoneNumbersController extends BaseController<PhoneNumberService, PhoneNumber, Integer> {
    private final PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumbersController(PhoneNumberService phoneNumberService) {
        super(phoneNumberService);
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<PhoneNumber>> getByUser(Integer id) {
        DataResult<PhoneNumber> result = this.phoneNumberService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<PhoneNumber>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<PhoneNumber>>(result, HttpStatus.BAD_REQUEST);
    }
}