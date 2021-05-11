package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualDao extends JpaRepository<Individual, Integer> {
}
