package hrms.dataAccess.abstracts;

import hrms.entities.concretes.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber, Integer> {
}
