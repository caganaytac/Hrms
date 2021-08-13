package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.UserBiography;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserBiographyDao extends BaseDao<UserBiography, Long>, JpaRepository<UserBiography, Long> {
    @Query("From UserBiography where user.id = :id and active = true")
    UserBiography getByUser(Integer id);

    @Query("From UserBiography where id = :id and user.id = :userId and biography = :biography and active = true")
    UserBiography doesExist(Long id, Integer userId, String biography);
}