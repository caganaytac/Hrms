package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.entities.concretes.IndividualTechnology;

public interface IndividualTechnologyDao extends JpaRepository<IndividualTechnology, Long> {
    List<IndividualTechnology> getByActive(boolean active);

    IndividualTechnology getByIdAndActive(Long id, boolean active);

    @Query("From IndividualTechnology where individual.id=:id and active=:active")
    List<IndividualTechnology> getByIndividual(int id, boolean active);
}