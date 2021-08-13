package hrms.dataAccess.abstracts;

import hrms.core.dataAccess.BaseDao;
import hrms.entities.concretes.SchoolFaculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolFacultyDao extends BaseDao<SchoolFaculty, Integer>, JpaRepository<SchoolFaculty, Integer> {
    @Query("From SchoolFaculty where faculty.id = :id and active = true")
    List<SchoolFaculty> getByFaculty(Short id);
 
    @Query("From SchoolFaculty where school.id = :id and active = true")
    List<SchoolFaculty> getBySchool(Integer id);

    @Query("From SchoolFaculty where school.id = :schoolId and faculty.id = :facultyId and active = true")
    SchoolFaculty doesExist(Integer schoolId, Short facultyId);
}