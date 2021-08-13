package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hrms.core.api.BaseController;
import hrms.core.utilities.results.DataResult;
import hrms.entities.concretes.FavoriteJobAdvert;
import hrms.business.abstracts.FavoriteJobAdvertService;

@RestController
@RequestMapping("/api/favoriteJobAdverts")
public class FavoriteJobAdvertsController extends BaseController<FavoriteJobAdvertService, FavoriteJobAdvert, Long> {
    private final FavoriteJobAdvertService favoriteJobAdvertService;

    @Autowired
    public FavoriteJobAdvertsController(FavoriteJobAdvertService favoriteJobAdvertService) {
        super(favoriteJobAdvertService);
        this.favoriteJobAdvertService = favoriteJobAdvertService;
    }

    @GetMapping("/getByIndividual")
    public ResponseEntity<DataResult<List<FavoriteJobAdvert>>> getbyIndividual(Integer id) {
        DataResult<List<FavoriteJobAdvert>> result = this.favoriteJobAdvertService.getByIndividual(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<FavoriteJobAdvert>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<FavoriteJobAdvert>>>(result, HttpStatus.BAD_REQUEST);
    }
}