package service;

import entity.*;

import java.util.List;
import java.util.Set;

public interface CourseTeacherService extends BaseEntityService<CourseTeacher,Integer> {
    List<Set<CourseTeacher>> getCourseTeachersByDepartment(String uniqStudentId);
    List<Set<CourseTeacher>> getCourseTeachersByStudentAndCurrentTerm(Student student, Term currentTerm);
    //1-    Set<CourseTeacher> updateCourseTeachers(String newStringCourse, Course updatedCourse);
    CourseTeacher update(String newStringCourse,CourseTeacher courseTeacher);
    Boolean removeCourseAndAllCourseTeachers(String courseUniqId);
    List<CourseTeacher> findByTeacher(Teacher teacher);

}
