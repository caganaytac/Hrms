package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.FavoriteJobAdvert;

public interface FavoriteJobAdvertDao extends BaseDao<FavoriteJobAdvert, Long>, JpaRepository<FavoriteJobAdvert, Long> {
    @Query("From FavoriteJobAdvert where individual.id = :id and active = true")
    List<FavoriteJobAdvert> getByIndividual(Integer id);

    @Query("From FavoriteJobAdvert where jobAdvert.id = :jobAdvertId and"
            + " individual.id = :individualId and active = true")
    FavoriteJobAdvert doesExist(Long jobAdvertId, Integer individualId);
}