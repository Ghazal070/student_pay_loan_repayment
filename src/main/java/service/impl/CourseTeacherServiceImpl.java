package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CourseTeacherUpdate;
import entity.*;
import repository.CourseTeacherRepository;
import service.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseTeacherServiceImpl extends BaseEntityServiceImpl<
        CourseTeacherRepository, CourseTeacher, Integer> implements CourseTeacherService {
    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final ObjectMapper objectMapper;
    private final TeacherService teacherService;
    private final TermService termService;
    private final CourseService courseService;

    public CourseTeacherServiceImpl(CourseTeacherRepository repository, DepartmentService departmentService, StudentService studentService, ObjectMapper objectMapper, TeacherService teacherService, TermService termService, CourseService courseService) {
        super(repository);
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.objectMapper = objectMapper;
        this.teacherService = teacherService;
        this.termService = termService;
        this.courseService = courseService;
    }

    @Override
    public List<Set<CourseTeacher>> getCourseTeachersByDepartment(String uniqStudentId) {
        List<Set<CourseTeacher>> setList = null;
        if (!uniqStudentId.isBlank()) {
            Student student = studentService.findByUsername(uniqStudentId);
            if (student != null) {
                Department department = student.getDepartment();
                if (department != null) {
                    Set<Course> courseList = department.getCourseList();
                    setList = courseList.stream().map(Course::getCourseTeachers).toList();
                }
            }
        }
        return setList;
    }

    @Override
    public List<Set<CourseTeacher>> getCourseTeachersByStudentAndCurrentTerm(Student student, Term currentTerm) {
        List<Set<CourseTeacher>> setList = null;
        Department department = student.getDepartment();
        if (department != null) {
            Set<Course> courseList = department.getCourseList();
            setList = courseList.stream()
                    .map(Course::getCourseTeachers)
                    .map(courseTeachers -> courseTeachers.stream()
                            .filter(teacher -> teacher.getTerm().getUniqId().equals(currentTerm.getUniqId())) // Filter by term
                            .collect(Collectors.toSet()))
                    .filter(teachersSet -> !teachersSet.isEmpty())
                    .collect(Collectors.toList());
        }
        return setList;
    }
//1-    @Override
//    public Set<CourseTeacher> updateCourseTeachers(String newStringCourse, Course updatedCourse) {
//        Set<CourseTeacher> courseTeachers;
//        courseTeachers = updatedCourse.getCourseTeachers();
//        if (courseTeachers != null && !courseTeachers.isEmpty()) {
//            courseTeachers.forEach(a -> {
//                a.setCourse(updatedCourse);
//                repository.update(a);
//            });
//        }
//        return courseTeachers;
//    }

    @Override
    public CourseTeacher update(String newStringCourse, CourseTeacher courseTeacher) {
        CourseTeacherUpdate courseTeacherUpdate;
        try {
            courseTeacherUpdate = objectMapper.readValue(newStringCourse, CourseTeacherUpdate.class);
            if (courseTeacherUpdate.teacherUniqId() != null && !courseTeacherUpdate.teacherUniqId().isEmpty()) {
                Teacher teacher = teacherService.findByUniqId(courseTeacherUpdate.teacherUniqId());
                courseTeacher.setTeacher(teacher);
            }
            if (courseTeacherUpdate.courseUniqId() != null && !courseTeacherUpdate.courseUniqId().isEmpty()) {
                Course course = courseService.findByUniqId(courseTeacherUpdate.courseUniqId());
                courseTeacher.setCourse(course);
            }
            if (courseTeacherUpdate.termUniqId() != null && !courseTeacherUpdate.termUniqId().isEmpty()) {
                Term term = termService.findByUniqId(courseTeacherUpdate.termUniqId());
                courseTeacher.setTerm(term);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return repository.update(courseTeacher);
    }

    @Override
    public Boolean removeCourseAndAllCourseTeachers(String courseUniqId) {
        if (!courseUniqId.isBlank()) {
            Course course = courseService.findByUniqId(courseUniqId);
            if (course != null) {
                Set<CourseTeacher> courseTeachers = course.getCourseTeachers();
                if (courseTeachers != null && !courseTeachers.isEmpty()) {
                    List<String> listUniqIdCourseTeachers = courseTeachers.stream().map(CourseTeacher::getUniqId).toList();
                    for (String uniqId : listUniqIdCourseTeachers) {
                        repository.deleteByUniqId(uniqId);
                    }
                    return courseService.deleteByUniqId(courseUniqId);
                }
            }
        }
        return false;
    }

    @Override
    public List<CourseTeacher> findByTeacher(Teacher teacher) {
        if (teacher!=null)
            return repository.findByTeacher(teacher);
        return Collections.emptyList();
    }
}
