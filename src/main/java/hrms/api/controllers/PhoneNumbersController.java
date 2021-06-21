package hrms.api.controllers;

import hrms.business.abstracts.PhoneNumberService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phoneNumbers")
public class PhoneNumbersController {
    private PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumbersController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<PhoneNumber>>> getAll() {
        var result = this.phoneNumberService.getAll();
        if (result.isSuccess()) return new ResponseEntity<DataResult<List<PhoneNumber>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<PhoneNumber>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<PhoneNumber>> getById(@RequestParam int id) {
        var result = this.phoneNumberService.getById(id);
        if (result.isSuccess()) return new ResponseEntity<DataResult<PhoneNumber>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<PhoneNumber>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody PhoneNumber phoneNumber) {
        var result = this.phoneNumberService.add(phoneNumber);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody PhoneNumber phoneNumber) {
        var result = this.phoneNumberService.update(phoneNumber);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody PhoneNumber phoneNumber) {
        var result = this.phoneNumberService.delete(phoneNumber);
        if (result.isSuccess()) return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}