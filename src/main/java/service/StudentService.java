package service;

import entity.Student;

import java.util.List;

public interface StudentService extends PersonService<Student>{
    //List<Course> loadAllCourse(Integer uniqId);
    List<Course> loadAllDepartmentCourse(Integer uniqId);
    Student update(String newStringStudent,Student existEntity);

}
