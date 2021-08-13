package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.UserPhoto;

public interface UserPhotoDao extends BaseDao<UserPhoto, Long>, JpaRepository<UserPhoto, Long> {
    @Query("From UserPhoto where user.id=:id and active=true")
    UserPhoto getByUser(Integer id);

    @Query("From UserPhoto where user.id = :userId and publicId = :publicId and url = :url and active = true")
    UserPhoto doesExist(Integer userId, String publicId, String url);
}