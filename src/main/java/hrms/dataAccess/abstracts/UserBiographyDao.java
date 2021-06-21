package hrms.dataAccess.abstracts;

import hrms.entities.concretes.UserBiography;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBiographyDao extends JpaRepository<UserBiography, Integer> {
    List<UserBiography> getByActive(boolean active);

    UserBiography getByIdAndActive(Long id, boolean active);
}