package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.core.utilities.results.DataResult;
import hrms.entities.dtos.CvDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hrms.business.abstracts.CvService;

@RestController
@RequestMapping("/api/cvs")
public class CvsController {
    private final CvService cvService;

    @Autowired
    public CvsController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<CvDto>> getByIndividual(@RequestParam Integer id) {
        DataResult<CvDto> result = this.cvService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<CvDto>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<CvDto>>(result, HttpStatus.BAD_REQUEST);
    }
}