package repository;

import java.util.List;

public interface CourseTeacherRepository extends BaseEntityRepository<CourseTeacher,Integer>{

    List<CourseTeacher> findByTeacher(Teacher teacher) ;
}
