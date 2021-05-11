package hrms.api.controllers;

import hrms.business.abstracts.PhoneNumberService;
import hrms.entities.concretes.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/phonenumbers")
public class PhoneNumbersController {
    private PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumbersController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/getall")
    public List<PhoneNumber> getAll() {
        return this.phoneNumberService.getAll();
    }
}