package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.entities.concretes.JobAdvert;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Long> {
    List<JobAdvert> getByActive(boolean active);

    JobAdvert getByIdAndActive(Long id, boolean active);

    List<JobAdvert> getByCorporate_IdAndActive(int id, boolean active);

    @Query("From JobAdvert where corporate.id=:id and active=true")
    List<JobAdvert> getAllByCorporateAndActive(int id);
}