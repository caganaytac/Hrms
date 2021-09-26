package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.business.abstracts.IndividualService;
import hrms.business.abstracts.CorporateService;
import hrms.business.abstracts.UserPhotoService;
import hrms.business.abstracts.PhoneNumberService;
import hrms.business.constans.Messages;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.*;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import hrms.entities.concretes.Individual;
import hrms.entities.concretes.Corporate;
import hrms.entities.concretes.PhoneNumber;
import hrms.entities.concretes.UserPhoto;
import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    private final IndividualService individualService;
    private final CorporateService corporateService;
    private final PhoneNumberService phoneNumberService;
    private final UserPhotoService userPhotoService;
    private final String USER = "User";

    @Autowired
    public UserManager(UserDao userDao, IndividualService individualService, CorporateService corporateService,
            PhoneNumberService phoneNumberService, UserPhotoService userPhotoService) {
        this.userDao = userDao;
        this.phoneNumberService = phoneNumberService;
        this.individualService = individualService;
        this.corporateService = corporateService;
        this.userPhotoService = userPhotoService;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(this.userDao.getByConfirmedAndActive(true, true));
    }

    @Override
    public DataResult<User> getById(Integer id) {
        return new SuccessDataResult<User>(this.userDao.getByIdAndActiveAndConfirmed(id, true, true));
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.getByEmailAndActive(email, true));
    }

    @Override
    public Result confirm(Integer id) {
        var user = this.userDao.getByIdAndActive(id, true);
        if (user == null)
            return new ErrorResult(Messages.notFound(USER));
        if (user.isConfirmed())
            return new ErrorResult(Messages.userAlreadyConfirmed);

        user.setConfirmed(true);
        this.userDao.save(user);
        return new SuccessResult(Messages.userConfirmed);
    }

    @Override
    public Result add(@Valid User user) {
        user.setEmail(user.getEmail().replaceAll("\\s", "").toLowerCase());
        var result = BusinessRules.run(doesExist(user));
        if (result != null)
            return result;

        user.setCreateDate(LocalDateTime.now());
        user.setConfirmed(false);
        user.setActive(true);

        this.userDao.save(user);
        return new SuccessResult();
    }

    @Override
    public Result update(@Valid User user) {
        user.setEmail(user.getEmail().replaceAll("\\s", "").toLowerCase());
        var result = BusinessRules.run(doesExistById(user.getId()), isConfirmed(user.getId()), doesExist(user));
        if (result != null)
            return result;

        var newUser = this.userDao.getByIdAndActive(user.getId(), true);
        newUser.setEmail(user.getEmail());

        this.userDao.save(newUser);
        return new SuccessResult(Messages.updated(USER));
    }

    @Override
    public Result delete(Integer id) {
        var result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        Individual individual = this.individualService.getByUser(id).getData();
        if (individual != null) {
            this.individualService.delete(individual.getId());
        }

        Corporate corporate = this.corporateService.getByUser(id).getData();
        if (corporate != null) {
            this.corporateService.delete(corporate.getId());
        }

        UserPhoto userPhoto = this.userPhotoService.getByUser(id).getData();
        if (userPhoto != null) {
            this.userPhotoService.delete(userPhoto);
        }
        
        PhoneNumber phoneNumber = this.phoneNumberService.getByUser(id).getData();
        if (phoneNumber != null) {
            this.phoneNumberService.delete(phoneNumber.getId());
        }

        var oldUser = this.userDao.getByIdAndActive(id, true);
        oldUser.setActive(false);

        this.userDao.save(oldUser);
        return new SuccessResult(Messages.deleted(USER));
    }

    private Result doesExist(User user) {
        var result = getByEmail(user.getEmail()).getData();
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(USER));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        var result = this.userDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(USER));
        return new SuccessResult();
    }

    private Result isConfirmed(Integer id) {
        var result = this.userDao.getByIdAndActiveAndConfirmed(id, false, true);
        if (result == null)
            return new ErrorResult(Messages.userNotConfirmed);
        return new SuccessResult();
    }
}