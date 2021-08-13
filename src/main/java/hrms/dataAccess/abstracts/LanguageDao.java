package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.Language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LanguageDao extends BaseDao<Language, Short>, JpaRepository<Language, Short> {
    @Query("From Language where name = :name and active = true")
    Language getByName(String name);
}