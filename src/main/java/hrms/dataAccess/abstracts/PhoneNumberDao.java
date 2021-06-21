package hrms.dataAccess.abstracts;

import hrms.entities.concretes.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber, Integer> {
    List<PhoneNumber> getByActive(boolean active);
    PhoneNumber getByIdAndActive(int id, boolean active);
}