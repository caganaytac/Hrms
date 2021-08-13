package hrms.business.concretes;

import hrms.business.abstracts.IndividualLanguageService;
import hrms.business.abstracts.IndividualService;
import hrms.business.constans.Messages;
import hrms.core.business.adapters.personService.Person;
import hrms.core.business.adapters.personService.PersonService;
import hrms.core.utilities.business.BusinessRules;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessDataResult;
import hrms.core.utilities.results.SuccessResult;
import hrms.dataAccess.abstracts.IndividualDao;
import hrms.entities.concretes.Individual;
import hrms.entities.concretes.IndividualLanguage;
import hrms.entities.dtos.CvDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IndividualManager implements IndividualService {
    private IndividualDao individualDao;
    private IndividualLanguageService individualLanguageService;
    private PersonService personService;
    private final String INDIVIDUAL = "Individual";

    @Autowired
    public IndividualManager(IndividualDao individualDao, IndividualLanguageService individualLanguageService,
            PersonService personService) {
        this.individualDao = individualDao;
        this.individualLanguageService = individualLanguageService;
        this.personService = personService;
    }

    @Override
    public DataResult<List<Individual>> getAll() {
        return new SuccessDataResult<List<Individual>>(this.individualDao.getByActive(true));
    }

    @Override
    public DataResult<Individual> getById(Integer id) {
        return new SuccessDataResult<Individual>(this.individualDao.getByIdAndActive(id, true));
    }

    @Override
    public DataResult<Individual> getByUser(Integer id) {
        return new SuccessDataResult<Individual>(this.individualDao.getByUser(id));
    }

    @Override
    public DataResult<List<CvDto>> getCv(Integer id) {
        return new SuccessDataResult<List<CvDto>>(null);
    }

    @Override
    public Result add(Individual individual) {
        individual.setNationalIdentity(individual.getNationalIdentity().replaceAll("\\s", ""));
        individual.setFirstName(individual.getFirstName().replaceAll("\\s", ""));
        individual.setLastName(individual.getLastName().replaceAll("\\s", ""));
        Result result = BusinessRules.run(checkRealPerson(individual), doesExist(individual));
        if (result != null)
            return result;

        individual.setCreateDate(LocalDateTime.now());
        individual.setActive(true);

        this.individualDao.save(individual);
        return new SuccessResult();
    }

    @Override
    public Result update(Individual individual) {
        individual.setNationalIdentity(individual.getNationalIdentity().replaceAll("\\s", ""));
        individual.setFirstName(individual.getFirstName().trim());
        individual.setLastName(individual.getLastName().replaceAll("\\s", ""));
        Result result = BusinessRules.run(doesExistById(individual.getId()), doesExist(individual),
                checkRealPerson(individual));
        if (result != null)
            return result;

        Individual oldIndividual = this.individualDao.getByIdAndActive(individual.getId(), true);
        individual.setUser(oldIndividual.getUser());
        individual.setCreateDate(oldIndividual.getCreateDate());
        individual.setActive(true);

        this.individualDao.save(individual);
        return new SuccessResult();
    }

    @Override
    public Result delete(Integer id) {
        Result result = BusinessRules.run(doesExistById(id));
        if (result != null)
            return result;

        List<IndividualLanguage> individualLanguages = this.individualLanguageService.getByIndividual(id).getData();
        if (individualLanguages.size() > 0)
            for (IndividualLanguage individualLanguage : individualLanguages) {
                this.individualLanguageService.delete(individualLanguage.getId());
            }

        Individual oldIndividual = this.individualDao.getByIdAndActive(id, true);
        oldIndividual.setActive(false);

        this.individualDao.save(oldIndividual);
        return new SuccessResult();
    }

    private Result checkRealPerson(Individual individual) {
        Person person = new Person(Long.parseLong(individual.getNationalIdentity()), individual.getFirstName(),
        individual.getLastName(), individual.getDateOfBirth().getYear());
        boolean result = this.personService.verify(person);
        if (!result)
            return new ErrorResult(Messages.notRealPerson);
        return new SuccessResult();
    }

    private Result doesExist(Individual individual) {
        Integer userId = individual.getUser().getId();
        String firstName = individual.getFirstName();
        String lastName = individual.getLastName();
        String nationalIdentity = individual.getNationalIdentity();
        LocalDate dateOfBirth = individual.getDateOfBirth();
        Individual result = this.individualDao.doesExist(userId, firstName, lastName, nationalIdentity, dateOfBirth);
        if (result != null)
            return new ErrorResult(Messages.alreadyExists(INDIVIDUAL));
        return new SuccessResult();
    }

    private Result doesExistById(Integer id) {
        Individual result = this.individualDao.getByIdAndActive(id, true);
        if (result == null)
            return new ErrorResult(Messages.notFound(INDIVIDUAL));
        return new SuccessResult();
    }
}