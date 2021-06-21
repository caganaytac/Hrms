package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.UserPhoto;

public interface UserPhotoDao extends JpaRepository<UserPhoto, Long>{
    List<UserPhoto> getByActive(boolean active);
    UserPhoto getByIdAndActive(Long id, boolean active);
}