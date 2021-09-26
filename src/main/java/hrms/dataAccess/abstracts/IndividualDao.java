package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Individual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface IndividualDao extends BaseDao<Individual, Integer>, JpaRepository<Individual, Integer> {
        Individual getByNationalIdentity(String nationalIdentity);

        @Query("From Individual where user.id = :id and active = true")
        Individual getByUser(Integer id);

        @Query("From Individual where user.id = :userId and firstName = :firstName and lastName = :lastName and"
                        + " nationalIdentity = :nationalIdentity and dateOfBirth = :dateOfBirth and active = true")
        Individual doesExist(Integer userId, String firstName, String lastName, String nationalIdentity,
                        LocalDate dateOfBirth);

        // @Query("Select new hrms.entities.dtos.CvDto(i,up,ub,l,g,it,il)"
        // + " From User u Inner Join u.individuals i Inner Join u.userPhotos up"
        // + " Inner Join u.userBiographies ub Inner Join u.linkedinAccounts l"
        // + " Inner Join u.githubAccounts g Inner Join"
        // + " u.individuals.individualTechnologies it"
        // + " Inner Join u.individuals.individualLanguages il where i.id = :id")
        // List<CvDto> getCv(Integer id);

        //   @Query("Select new hrms.entities.dtos.CvDto(i,up,ub,l,g)"
        // + " From User u Inner Join u.individual i Inner Join u.userPhotos up"
        // + " Inner Join u.userBiographies ub Inner Join u.linkedinAccounts l"
        // + " Inner Join u.githubAccounts g Inner Join where i.id = :id")
        // List<CvDto> getCv(Integer id);
}
// Inner Join i.individualTechnologies it