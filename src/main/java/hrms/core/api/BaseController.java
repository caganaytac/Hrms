package hrms.core.api;

import hrms.core.business.baseService.BaseService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

public class BaseController<TEntityService extends BaseService<TEntity, Id>, TEntity, Id> {
    private final TEntityService entityService;

    public BaseController(final TEntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<TEntity>>> getAll() {
        DataResult<List<TEntity>> result = this.entityService.getAll();
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<TEntity>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<TEntity>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<TEntity>> getById(@RequestParam final Id id) {
        DataResult<TEntity> result = this.entityService.getById(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<TEntity>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<TEntity>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid final TEntity entity) {
        Result result = this.entityService.add(entity);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid final TEntity entity) {
        Result result = this.entityService.update(entity);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam final Id id) {
        Result result = this.entityService.delete(id);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}