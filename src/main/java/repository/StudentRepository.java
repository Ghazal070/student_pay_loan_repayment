package repository;

import entity.Student;

import java.util.List;

public interface StudentRepository extends PersonRepository<Student>{
    List<Course> loadAllCourse(Integer uniqId);
    List<Course> loadAllDepartmentCourse(Integer uniqId);
}
