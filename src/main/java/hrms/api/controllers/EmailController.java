package hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.core.utilities.email.EmailMessage;
import hrms.core.utilities.email.EmailService;
import hrms.core.utilities.results.Result;

@RequestMapping("api/email")
@RestController
public class EmailController {
    private final EmailService emailService;
   
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Result> registerForIndividual(@RequestBody @Valid EmailMessage emailMessage) {
        Result result = this.emailService.send(emailMessage);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}