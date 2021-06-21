package hrms.dataAccess.abstracts;

import hrms.entities.concretes.IndividualLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualLanguageDao extends JpaRepository<IndividualLanguage, Long> {
    List<IndividualLanguage> getByActive(boolean active);
    IndividualLanguage getByIdAndActive(long id, boolean active);
}