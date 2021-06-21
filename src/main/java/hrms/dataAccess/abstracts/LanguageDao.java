package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageDao extends JpaRepository<Language, Short> {
    List<Language> getByActive(boolean active);
    Language getByIdAndActive(short id, boolean active);
}