package hrms.api.controllers;

import hrms.business.abstracts.CityService;
import hrms.core.api.BaseController;
import hrms.entities.concretes.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CitiesController extends BaseController<CityService, City, Short> {
    @Autowired
    public CitiesController(CityService cityService) {
        super(cityService);
    }
}