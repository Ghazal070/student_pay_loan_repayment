package menu;

import entity.*;
import menu.util.Input;
import menu.util.Massage;
import service.*;
import util.AuthHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TeacherLogin {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final TeacherService teacherService;
    private final AuthHolder authHolder;
    private final CourseStudentService courseStudentService;
    private final CourseTeacherService courseTeacherService;
    private final TermService termService;

    public TeacherLogin(Input INPUT, Massage MASSAGE, TeacherService teacherService, AuthHolder authHolder
            , CourseStudentService courseStudentService, CourseTeacherService courseTeacherService, TermService termService) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.teacherService = teacherService;
        this.authHolder = authHolder;
        this.courseStudentService = courseStudentService;
        this.courseTeacherService = courseTeacherService;
        this.termService = termService;
    }

    public void show() {
        TeacherLoginMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Display information 
                    2- Registration student score
                    3- View the pay slip
                    4- Previous Menu
                    5- Exit
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    Teacher teacherById = teacherService.findById(authHolder.tokenId);
                    System.out.println(teacherById);
                    break;
                }
                case "2": {
                    Teacher teacher = teacherService.findById(authHolder.tokenId);

                    List<CourseTeacher> courseTeachers = courseTeacherService.findByTeacher(teacher);
                    if (courseTeachers != null) {
                        List<CourseStudent> courseStudents = courseTeachers.stream()
                                .map(CourseTeacher::getCourseStudent).toList();
                        System.out.println("============CourseStudentList,You Can Grade==========");
                        courseStudents
                                .stream()
                                .filter(Objects::nonNull)
                                .forEach(System.out::println);
                        boolean flag = true;
                        while (flag) {
                            System.out.println("Enter courseStudentUniqId: ");
                            String courseStudentUniqId = INPUT.scanner.next();
                            List<CourseStudent> courseStudentList = courseStudents.stream()
                                    .filter(Objects::nonNull)
                                    .filter(courseStudent ->
                                            courseStudent.getUniqId().equals(courseStudentUniqId))
                                    .toList();
                            if (!courseStudentList.isEmpty()) {
                                System.out.println("Enter grade(0-20): ");
                                double grade = INPUT.scanner.nextDouble();
                                if (grade > 0 && grade < 20) {
                                    courseStudentList.get(0).setGrade(grade);
                                    courseStudentService.update(courseStudentList.get(0));
                                    System.out.println(MASSAGE.getSuccessfulMassage(authHolder.tokenName));
                                } else {
                                    System.out.println(MASSAGE.getFailMassage(authHolder.tokenName));
                                    System.out.println("Must Grade Between 0-20");
                                }

                            } else {
                                System.out.println(MASSAGE.getFailMassage(authHolder.tokenName));
                                System.out.println("Must Exist courseStudentUniqId in upper list");
                            }
                            System.out.println("Do you continue? (yes/no)");
                            String yes_no = INPUT.scanner.next();
                            if (yes_no.equals("no")) {
                                flag = false;
                            }
                        }
                    }
                    break;
                }
                case "3": {
                    Teacher teacher = teacherService.findById(authHolder.tokenId);
                    List<Term> terms = termService.loadAll();
                    System.out.println("==============TermList================");
                    if (terms != null && !terms.isEmpty()) terms.forEach(System.out::println);
                    System.out.println(MASSAGE.getInputMassage(Term_.UNIQ_ID));
                    String termUniqId = INPUT.scanner.next();
                    Term term = termService.findByUniqId(termUniqId);
                    int sumUnit = teacherService.getSumUnit(term, teacher);
                    Integer paySlip = 0;
                    if (term != null && teacher != null) {
                        switch (teacher.getTeacherType()) {
                            case Tuition: {
                                paySlip = teacherService.paySlipTuition(term);
                                break;
                            }
                            case ScienceCommittee: {
                                paySlip = teacherService.paySlipScienceCommittee(term);
                                break;
                            }
                        }
                        System.out.println("============================Information=====================");
                        System.out.println(teacher);
                        System.out.println("================UnitCounts : " + sumUnit + " =====================");
                        System.out.println(MASSAGE.getPayMassage(authHolder.tokenName) + paySlip);
                    }

                    break;
                }
                case "4": {
                    break TeacherLoginMenu;
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
