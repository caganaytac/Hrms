package hrms.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.JobAdvert;
import hrms.entities.dtos.JobAdvertDto;

public interface JobAdvertDao extends BaseDao<JobAdvert, Long>, JpaRepository<JobAdvert, Long> {
    @Query("Select new hrms.entities.dtos.JobAdvertDto(j,up,true)"
            + " From JobAdvert j Inner Join j.corporate.user.userPhotos up"
            + " where j.active = :active and up.active = :active")
    List<JobAdvertDto> getDetailsByActive(boolean active);


    @Query("Select new hrms.entities.dtos.JobAdvertDto(j,up,true)"
            + " From JobAdvert j Inner Join j.corporate.user.userPhotos up"
            + " where j.id = :id and j.active = :active and up.active = :active")
    JobAdvertDto getDetailByIdAndActive(Long id, boolean active);

    @Query("From JobAdvert where corporate.id = :id and active = true")
    List<JobAdvert> getByCorporateAndActive(int id);

    @Query("Select new hrms.entities.dtos.JobAdvertDto(j,up,true)"
            + " From JobAdvert j Inner Join j.corporate.user.userPhotos up"
            + " where j.corporate.id = :id and j.active = :active and up.active = :active")
    List<JobAdvertDto> getDetailsByCorporateAndActive(Integer id, boolean active);

    @Query("From JobAdvert where corporate.id = :corporateId and jobPosition.id = :jobPositionId and city.id = :cityId and"
            + " workArea.id = :workAreaId and workTime.id = :workTimeId and minSalary = :minSalary and maxSalary = :maxSalary and"
            + " openPosition = :openPosition and deadLine = :deadLine and description = :description and active = true")
    JobAdvert doesExist(Integer corporateId, Short jobPositionId, Short cityId, Short workAreaId, Short workTimeId,
            Double minSalary, Double maxSalary, Short openPosition, LocalDate deadLine, String description);
}