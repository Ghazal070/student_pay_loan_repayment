package menu;

import entity.*;
import menu.util.Input;
import menu.util.Massage;
import service.CourseStudentService;
import service.CourseTeacherService;
import service.StudentService;
import service.TermService;
import util.AuthHolder;

import java.util.List;
import java.util.Set;

public class StudentLogin {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final StudentService studentService;
    private final AuthHolder authHolder;
    private final CourseStudentService courseStudentService;
    private final CourseTeacherService courseTeacherService;
    private final TermService termService;


    public StudentLogin(Input INPUT, Massage MASSAGE, StudentService studentService, AuthHolder authHolder
            , CourseStudentService courseStudentService, CourseTeacherService courseTeacherService, TermService termService) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.courseStudentService = courseStudentService;
        this.courseTeacherService = courseTeacherService;
        this.termService = termService;
    }

    public void show() {
        StudentLoginMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Display information 
                    2- Display all course
                    3- Select course
                    4- Display all student course with grade
                    5- Previous Menu
                    6- Exit
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    Student studentById = studentService.findById(authHolder.tokenId);
                    System.out.println(studentById);
                    break;
                }
                case "2": {
                    System.out.println("====================DepartmentCourse====================");
                    List<Course> courses = studentService.loadAllDepartmentCourse(authHolder.tokenId);
                    courses.forEach(System.out::println);
                    System.out.println("====================DepartmentCourseTeacher====================");
                    List<Set<CourseTeacher>> setList = courseTeacherService
                            .getCourseTeachersByDepartment(authHolder.tokenName);
                    if (setList != null && !setList.isEmpty()) {
                        setList.forEach(s -> {
                            if (s != null)
                                s.forEach(System.out::println);
                        });
                    }
                    break;
                }
                case "3": {
                    System.out.println("Existing Terms: ");
                    List<Term> terms = termService.loadAll();
                    if (terms != null && !terms.isEmpty()) terms.forEach(System.out::println);
                    System.out.println(MASSAGE.getInputMassage(" Current" + Term_.TITLE.toUpperCase() + "Term"));
                    String currentTermTitle = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(" Previous" + Term_.TITLE.toUpperCase() + "Term"));
                    String previousTermTitle = INPUT.scanner.next();
                    Student student = studentService.findByUsername(authHolder.tokenName);
                    Term currentTerm = termService.findByUniqId(currentTermTitle);
                    Term previousTerm = termService.findByUniqId(previousTermTitle);
                    List<Set<CourseTeacher>> courseTeachersByDepartment = null;
                    if (student != null && currentTerm != null) {
                        courseTeachersByDepartment = courseTeacherService
                                .getCourseTeachersByStudentAndCurrentTerm(student, currentTerm);
                        courseTeachersByDepartment.stream().forEach(s -> {
                            if (s != null)
                                s.forEach(System.out::println);
                        });
                        System.out.println(MASSAGE.getInputMassage(CourseTeacher_.UNIQ_ID + "CourseTeacher"));
                        String courseTeacherUniqId = INPUT.scanner.next();
                        CourseTeacher courseTeacher = courseTeacherService.findByUniqId(courseTeacherUniqId);
                        if (previousTerm != null && courseTeacher != null) {
                            Boolean selectUnitResult = courseStudentService
                                    .selectUnit(student, currentTerm, previousTerm, courseTeacher);
                            if (selectUnitResult) {
                                CourseStudent courseStudent = CourseStudent.builder()
                                        .student(student)
                                        .term(currentTerm)
                                        .courseTeacher(courseTeacher)
                                        .build();
                                courseStudentService.save(courseStudent);
                                courseStudent.setUniqId(String.valueOf(courseStudent.getId()));
                                CourseStudent updateCourseStudent = courseStudentService.update(courseStudent);

                                if (updateCourseStudent != null)
                                    System.out.println(MASSAGE.getSuccessfulMassage(authHolder.tokenName));
                            } else System.out.println(MASSAGE.getFailMassage(authHolder.tokenName));
                        } else System.out.println(MASSAGE.getFailMassage(authHolder.tokenName));
                    } else System.out.println(MASSAGE.getFailMassage(authHolder.tokenName));

                    break;
                }
                case "4": {
                    List<CourseTeacher> setList = courseStudentService.loadAllCourseStudent(authHolder.tokenName);
                    if (setList != null && !setList.isEmpty()) setList.forEach(System.out::println);
                    else System.out.println(MASSAGE.getFailMassage(authHolder.tokenName));
                    break;
                }
                case "5": {
                    break StudentLoginMenu;
                }
                case "6": {
                    System.exit(0);
                }
                default:
                    System.out.println(MASSAGE.getInvalidMassage());
            }

        }
    }
}
