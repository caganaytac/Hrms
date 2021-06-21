package hrms.dataAccess.abstracts;

import hrms.entities.concretes.SchoolFaculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolFacultyDao extends JpaRepository<SchoolFaculty, Integer> {
    List<SchoolFaculty> getByActive(boolean active);

    SchoolFaculty getByIdAndActive(int id, boolean active);

    List<SchoolFaculty> getBySchool_Id(Integer id);

    @Query("From SchoolFaculty where school.id=:id")
    List<SchoolFaculty> getBySchool(Integer id);
}

//	  @Query("From Product where productName=:productName and category.categoryId=:categoryId")
