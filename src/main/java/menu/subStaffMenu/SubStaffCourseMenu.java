package menu.subStaffMenu;

import entity.*;
import menu.util.Input;
import menu.util.Massage;
import service.*;
import util.AuthHolder;
import validation.Validator;

import java.util.List;
import java.util.Set;

public class SubStaffCourseMenu {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final CourseService courseService;
    private final AuthHolder authholder;
    private final DepartmentService departmentService;
    private final TeacherService teacherService;
    private final TermService termService;
    private final CourseTeacherService courseTeacherService;


    public SubStaffCourseMenu(Input INPUT, Massage MASSAGE, CourseService courseService, AuthHolder authholder
            , DepartmentService departmentService, TeacherService teacherService, TermService termService
            , CourseTeacherService courseTeacherService) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.courseService = courseService;
        this.authholder = authholder;
        this.departmentService = departmentService;
        this.teacherService = teacherService;
        this.termService = termService;
        this.courseTeacherService = courseTeacherService;
    }


    public void show() {
        subStaffCourseMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Register Course Information  
                    2- Remove Course Information
                    3- Edit Course Information
                    4- Previous Menu
                    5- Exit
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println("""
                            1- Register a course and courseTeacher
                            2- Register a courseTeacher
                            """);
                    String input = INPUT.scanner.next();
                    switch (input) {
                        case "1": {
                            System.out.println(MASSAGE.getInputMassage(Course_.UNIQ_ID));
                            String courseCode = INPUT.scanner.next();
                            System.out.println(MASSAGE.getInputMassage(Course_.TITLE));
                            String title = INPUT.scanner.next();
                            System.out.println(MASSAGE.getInputMassage(Course_.UNIT_COUNT));
                            int unitCount = INPUT.scanner.nextInt();
                            Course saveCourse = courseService.save(Course.builder()
                                    .uniqId(courseCode)
                                    .title(title)
                                    .unitCount(unitCount)
                                    .build());
                            List<Department> departments = departmentService.loadAll();
                            System.out.println("Please choose one departmentCode: ");
                            departments.forEach(System.out::println);
                            String departmentCode = INPUT.scanner.next();
                            Department departmentByUniqId = departmentService.findByUniqId(departmentCode);
                            if (departmentByUniqId != null) {
                                saveCourse.setDepartment(departmentByUniqId);
                                courseService.update(saveCourse);
                            }
                            if (saveCourse != null) {
                                System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                                System.out.println("Do you want to assign a teacher to this course? (yes/no)");
                                String yes_no = INPUT.scanner.next();
                                if (yes_no.equals("yes")) {
                                    System.out.println(MASSAGE.getInputMassage(CourseTeacher_.UNIQ_ID));
                                    String courseTeacherUniqId = INPUT.scanner.next();
                                    List<Teacher> teachers = teacherService.loadAll();
                                    if (teachers != null) {
                                        System.out.println("Teachers List: Please select uniqId");
                                        teachers.forEach(System.out::println);
                                    }
                                    System.out.println(MASSAGE.getInputMassage(Teacher_.UNIQ_ID));
                                    String teacherUniqId = INPUT.scanner.next();
                                    Teacher teacher = teacherService.findByUniqId(teacherUniqId);
                                    List<Term> terms = termService.loadAll();
                                    if (terms != null) {
                                        System.out.println("Terms List: Please select uniqId");
                                        terms.forEach(System.out::println);
                                    }
                                    System.out.println(MASSAGE.getInputMassage(Term_.UNIQ_ID));
                                    String termUniqId = INPUT.scanner.next();
                                    Term term = termService.findByUniqId(termUniqId);
                                    if (teacher != null && term != null) {
                                        CourseTeacher courseTeacher = CourseTeacher.builder()
                                                .uniqId(courseTeacherUniqId)
                                                .course(saveCourse)
                                                .teacher(teacher)
                                                .term(term)
                                                .build();
                                        CourseTeacher saveCourseTeacher = courseTeacherService.save(courseTeacher);
                                        if (saveCourseTeacher != null)
                                            System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                                    }
                                }
                            } else System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                            break;
                        }
                        case "2": {
                            System.out.println(MASSAGE.getInputMassage(CourseTeacher_.UNIQ_ID));
                            String courseTeacherUniqId = INPUT.scanner.next();
                            List<Teacher> teachers = teacherService.loadAll();
                            if (teachers != null) {
                                System.out.println("Teachers List: Please select uniqId");
                                teachers.forEach(System.out::println);
                            }
                            System.out.println(MASSAGE.getInputMassage(Teacher_.UNIQ_ID));
                            String teacherUniqId = INPUT.scanner.next();
                            Teacher teacher = teacherService.findByUniqId(teacherUniqId);
                            List<Term> terms = termService.loadAll();
                            if (terms != null) {
                                System.out.println("Terms List: Please select uniqId");
                                terms.forEach(System.out::println);
                            }
                            System.out.println(MASSAGE.getInputMassage(Term_.UNIQ_ID));
                            String termUniqId = INPUT.scanner.next();
                            Term term = termService.findByUniqId(termUniqId);
                            List<Course> courses = courseService.loadAll();
                            if (courses != null) {
                                System.out.println("Courses List: Please select uniqId");
                                courses.forEach(System.out::println);
                            }
                            System.out.println(MASSAGE.getInputMassage(Course_.UNIQ_ID));
                            String courseUniqId = INPUT.scanner.next();
                            Course course = courseService.findByUniqId(courseUniqId);
                            if (teacher != null && term != null && course!=null) {
                                CourseTeacher courseTeacher = CourseTeacher.builder()
                                        .uniqId(courseTeacherUniqId)
                                        .course(course)
                                        .teacher(teacher)
                                        .term(term)
                                        .build();
                                CourseTeacher saveCourseTeacher = courseTeacherService.save(courseTeacher);
                                if (saveCourseTeacher != null)
                                    System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                            }
                            break;
                        }

                    }

                    break;
                }
                case "2": {
                    System.out.println("""
                            1- Delete a course and automatically delete all courseTeachers
                            2- Delete a courseTeacher
                            """);
                    String input = INPUT.scanner.next();

                    switch (input) {
                        case "1": {
                            List<Course> courses = courseService.loadAll();
                            if (courses != null && !courses.isEmpty()) {
                                System.out.println("Available Courses: ");
                                courses.forEach(System.out::println);
                            }
                            System.out.println(MASSAGE.getInputMassage(Course_.UNIQ_ID));
                            String courseNumber = INPUT.scanner.next();

                            Boolean deleteByCourseNumber = courseTeacherService
                                    .removeCourseAndAllCourseTeachers(courseNumber);
                            if (deleteByCourseNumber) {
                                System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                            } else {
                                System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                            }
                            break;
                        }
                        case "2": {
                            List<CourseTeacher> courseTeachers = courseTeacherService.loadAll();
                            if (courseTeachers != null) courseTeachers.forEach(System.out::println);
                            System.out.println(MASSAGE.getInputMassage(CourseTeacher_.UNIQ_ID));
                            String courseTeacherNumber = INPUT.scanner.next();
                            Boolean deleteByCourseTeacher = courseTeacherService.deleteByUniqId(courseTeacherNumber);
                            if (deleteByCourseTeacher) {
                                System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                            } else {
                                System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                            }
                            break;
                        }
                        default:
                            System.out.println(MASSAGE.getInvalidMassage());
                    }
                    break;

                }
                case "3": {
                    System.out.println("""  
                            1- Edit a course  
                            2- Edit a courseTeacher  
                            """);
                    String input = INPUT.scanner.next();

                    switch (input) {
                        case "1": {
                            List<Course> courses = courseService.loadAll();
                            if (courses != null && !courses.isEmpty()) {
                                System.out.println("Available Courses: ");
                                courses.forEach(System.out::println);
                            } else {
                                System.out.println("No courses available.");
                                break;
                            }
                            System.out.println(MASSAGE.getInputMassage(Course_.UNIQ_ID));
                            String courseNumber = INPUT.scanner.next();
                            Course course = courseService.findByUniqId(courseNumber);
                            if (course != null) {
                                System.out.println("Current Course Properties: ");
                                System.out.println(course);
                                System.out.println(MASSAGE.getInputMassage(" in this Pattern: {\"title\" : \"newTitle\",\"unitCount\" : \"5\", \"uniqIdDepartment\": \"1\"}"));
                                String newStringCourse = INPUT.scanner.next();
                                if (Validator.isValidPattern(newStringCourse, "courseUpdate")) {
                                    Course updatedCourse = courseService.update(newStringCourse, course);
                                    if (updatedCourse != null) {
                                        //1-Set<CourseTeacher> courseTeachers = courseTeacherService.updateCourseTeachers(newStringCourse, updatedCourse);
                                        System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                                    } else {
                                        System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                                    }
                                } else {
                                    System.out.println("Invalid input pattern. Please follow the specified format.");
                                }
                            } else {
                                System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                            }
                            break;
                        }
                        case "2": {
                            List<CourseTeacher> courseTeachers = courseTeacherService.loadAll();
                            if (courseTeachers != null && !courseTeachers.isEmpty()) {
                                System.out.println("Available Course Teachers: ");
                                courseTeachers.forEach(System.out::println);
                            } else {
                                System.out.println("No course teachers available.");
                                break;
                            }

                            System.out.println(MASSAGE.getInputMassage(CourseTeacher_.UNIQ_ID));
                            String courseTeacherNumber = INPUT.scanner.next();
                            CourseTeacher courseTeacher = courseTeacherService.findByUniqId(courseTeacherNumber);

                            if (courseTeacher != null) {
                                System.out.println("Current Course Teacher Properties: ");
                                System.out.println(courseTeacher);
                                System.out.println(MASSAGE.getInputMassage(" in this Pattern: {\"courseUniqId\" : \"1\",\"teacherUniqId\": \"1\",\"termUniqId\": \"1\"}"));
                                String newStringTeacher = INPUT.scanner.next();
                                if (Validator.isValidPattern(newStringTeacher, "courseTeacherUpdate")) {
                                    CourseTeacher updateCourseTeacher = courseTeacherService.update(newStringTeacher, courseTeacher);
                                    if (updateCourseTeacher != null)
                                        System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                                } else {
                                    System.out.println("Invalid input pattern. Please follow the specified format.");
                                }
                            } else {
                                System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                            }
                            break;
                        }
                        default:
                            System.out.println(MASSAGE.getInvalidMassage());
                    }
                    break;
                }
                case "4": {
                    break subStaffCourseMenu;
                }

                case "5": {
                    System.exit(0);
                }
                default:
                    System.out.println(MASSAGE.getInvalidMassage());
            }

        }
    }
}
