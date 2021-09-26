package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hrms.business.abstracts.UserPhotoService;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.Result;
import hrms.entities.concretes.UserPhoto;

@RestController
@RequestMapping("/api/userPhotos")
public class UserPhotosController {
    private final UserPhotoService userPhotoService;
    private final UsersController usersController;

    @Autowired
    public UserPhotosController(UserPhotoService userPhotoService, UsersController usersController) {
        this.userPhotoService = userPhotoService;
        this.usersController = usersController;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<UserPhoto>>> getAll() {
        DataResult<List<UserPhoto>> result = this.userPhotoService.getAll();
        if (result.isSuccess())
            return new ResponseEntity<DataResult<List<UserPhoto>>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<List<UserPhoto>>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<UserPhoto>> getById(@RequestParam Long id) {
        DataResult<UserPhoto> result = this.userPhotoService.getById(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<UserPhoto>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<UserPhoto>>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<DataResult<UserPhoto>> getByUser(@RequestParam Integer id) {
        DataResult<UserPhoto> result = this.userPhotoService.getByUser(id);
        if (result.isSuccess())
            return new ResponseEntity<DataResult<UserPhoto>>(result, HttpStatus.OK);
        return new ResponseEntity<DataResult<UserPhoto>>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestParam("userId") Integer userId,
            @RequestParam("file") MultipartFile multipartFile) {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setUser(this.usersController.getById(userId).getBody().getData());
        Result result = this.userPhotoService.add(userPhoto, multipartFile);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestParam Long id, @RequestParam MultipartFile multipartFile) {
        UserPhoto photo = getById(id).getBody().getData();
        Result result = this.userPhotoService.update(photo, multipartFile);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody UserPhoto userPhoto) {
        Result result = this.userPhotoService.delete(userPhoto);
        if (result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.OK);
        return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
    }
}