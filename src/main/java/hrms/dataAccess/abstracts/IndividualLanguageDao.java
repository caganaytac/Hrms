package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.IndividualLanguage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualLanguageDao extends BaseDao<IndividualLanguage, Long>, JpaRepository<IndividualLanguage, Long> {
    @Query("From IndividualLanguage where individual.id=:id and active=true")
    List<IndividualLanguage> getByIndividual(Integer id);

    @Query("From IndividualLanguage where language.id = :languageId and individual.id = :individualId and level = :level and active = true")
    IndividualLanguage doesExist(Short languageId, Integer individualId, Short level);
}