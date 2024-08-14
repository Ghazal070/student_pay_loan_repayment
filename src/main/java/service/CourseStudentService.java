package service;

import entity.*;

import java.util.List;

public interface CourseStudentService extends BaseEntityService<CourseStudent,Integer> {
    Boolean selectUnit(Student student, Term currentTerm, Term previousTerm,CourseTeacher courseTeacher);
    List<CourseTeacher> loadAllCourseStudent(String studentNumber);
    Boolean selectUnitBaseNotDuplicate(Student student, Course course);
}
