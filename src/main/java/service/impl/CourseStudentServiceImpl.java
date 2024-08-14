package service.impl;

import entity.*;
import repository.CourseStudentRepository;
import service.CourseService;
import service.CourseStudentService;
import service.StudentService;
import service.TermService;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CourseStudentServiceImpl extends BaseEntityServiceImpl<CourseStudentRepository
        , CourseStudent, Integer> implements CourseStudentService {
    private final TermService termService;
    private final StudentService studentService;
    private final CourseService courseService;

    public CourseStudentServiceImpl(CourseStudentRepository repository, TermService termService, StudentService studentService, CourseService courseService) {
        super(repository);
        this.termService = termService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public List<CourseTeacher> loadAllCourseStudent(String studentNumber) {
        List<CourseTeacher> sets = null;
        Student studentServiceByUniqIdyUniqId = studentService.findByUniqId(studentNumber);
        if (studentServiceByUniqIdyUniqId != null) {
            Set<CourseStudent> courseStudents = studentServiceByUniqIdyUniqId.getCourseStudents();
            if (!courseStudents.isEmpty()) {
                sets = courseStudents.stream().map(CourseStudent::getCourseTeacher).toList();
            }
        }
        return sets;
    }

    @Override
    public Boolean selectUnit(Student student, Term currentTerm, Term previousTerm, CourseTeacher courseTeacher) {
        Double average = repository.calculateAverageGrade(student, previousTerm);
        int sumUnit = calculateSumOfUnits(student);
        Boolean canSelectBasedOnAverage = (average != null) ? validateUnitSelectionByAverage(sumUnit, average) : true;
        Boolean noDuplicateUnit = selectUnitBaseNotDuplicate(student, courseTeacher.getCourse());
        return canSelectBasedOnAverage && noDuplicateUnit;

    }

    private int calculateSumOfUnits(Student student) {
        Set<CourseStudent> courseStudents = student.getCourseStudents();
        if (courseStudents != null)
            return courseStudents.stream()
                    .mapToInt(courseStudent -> {
                        CourseTeacher courseTeacher = courseStudent.getCourseTeacher();
                        if (courseTeacher != null) {
                            return courseTeacher.getCourse().getUnitCount();
                        }

                        return 0;
                    })
                    .sum();
        return 0;
    }

    private Boolean validateUnitSelectionByAverage(int sumUnit, Double average) {
        return (average > 18 && sumUnit < 24) || (average <= 18 && sumUnit < 20);
    }

    @Override
    public Boolean selectUnitBaseNotDuplicate(Student student, Course course) {
        Set<CourseStudent> courseStudents = student.getCourseStudents();
        if (courseStudents == null || courseStudents.isEmpty()) {
            return true;
        }
        boolean hasTakenCourse = courseStudents.stream()
                .map(CourseStudent::getCourseTeacher)
                .filter(Objects::nonNull)
                .map(CourseTeacher::getCourse)
                .filter(Objects::nonNull)
                .anyMatch(existingCourse -> existingCourse.equals(course));
        if (!hasTakenCourse) {
            return true;
        }
        return courseStudents.stream()
                .filter(courseStudent -> {
                    CourseTeacher courseTeacher = courseStudent.getCourseTeacher();
                    return courseTeacher != null && courseTeacher.getCourse() != null
                            && courseTeacher.getCourse().equals(course);
                })
                .allMatch(courseStudent -> {
                    Double grade = courseStudent.getGrade();
                    if (grade == null) return false;
                    return grade < 10;
                });
    }
}
