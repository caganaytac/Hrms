package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Individual;
import hrms.entities.dtos.CvDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualDao extends JpaRepository<Individual, Integer> {
    List<Individual> getByActive(boolean active);

    Individual getByIdAndActive(int id, boolean active);

    // @Query("Select new hrms.entities.dtos.CvDto(i,up,ub,l,g,ie,ije,it,il)"
    //         + "From Individual i Inner Join i.individualEducations ie From Individual Left Join i.user.userPhotos up"
    //         + "Left Join i.user.userBiographies ub Left Join i.user.linkedinAccounts l"
    //         + "Left Join i.user.githubAccounts g Left Join i.individualJobExperiences ije Left Join i.individualTechnologies it"
    //         + "Left Join i.individualLanguages il where i.id=:id and i.active=true")
    // CvDto getCv(Integer id);


    // @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto"
    //         + "(p.id, p.productName, c.categoryName) " + "From Category c Inner Join c.products p")
    // List<ProductWithCategoryDto> getProductWithCategoryDetails();

    // "(individual,userPhoto,userBiography,linkedinAccount,githubAccount,individualEducations
    // ,individualJobExperiences,individualTechnologies,individualLanguages)"
}