package menu;

import entity.Student;
import menu.util.Input;
import menu.util.Massage;
import service.*;
import util.AuthHolder;

public class Menu {
    private final Input INPUT;
    private final StaffLogin staffLogin;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final StaffService staffService;
    private final AuthHolder authHolder;
    private final Massage MASSAGE;
    private final StudentLogin studentLogin;
    private final TeacherLogin teacherLogin;

    public Menu(Input input, StaffLogin staffLogin, AuthHolder authHolder, Massage massage, StudentLogin studentLogin
            , StudentService studentService, TeacherService teacherService, StaffService staffService, TeacherLogin teacherLogin) {

        this.INPUT = input;
        this.studentService = studentService;
        this.staffLogin = staffLogin;
        this.teacherService = teacherService;
        this.staffService = staffService;
        this.authHolder = authHolder;
        this.MASSAGE = massage;
        this.studentLogin = studentLogin;
        this.teacherLogin = teacherLogin;
    }

    public void show() {

        System.out.println("Welcome to my program");
        while (true) {
            System.out.println("Enter your information ");
            System.out.println("Enter username: ");
            String username = INPUT.scanner.next();
            System.out.println("Enter password: ");
            String password = INPUT.scanner.next();
            Person login = null;
            if (isStudent(username)) {
                login = studentService.login(username, password);
            } else if (isTeacher(username)) {
                login = teacherService.login(username, password);
            } else if (isStaff(username)) {
                login = staffService.login(username, password);
            }

            if (login != null) {
                authHolder.tokenName = login.getUsername();
                authHolder.tokenId = login.getId();
                if (login instanceof Student) {
                    studentLogin.show();
                } else if (login instanceof Teacher) {
                    teacherLogin.show();
                } else if (login instanceof Staff) {
                    staffLogin.show();
                }
            } else {
                MASSAGE.getFailMassage(username);
            }
        }

    }

    private boolean isStudent(String username) {

        return studentService.findByUsername(username) != null;
    }

    private boolean isTeacher(String username) {
        return teacherService.findByUsername(username) != null;
    }

    private boolean isStaff(String username) {
        return staffService.findByUsername(username) != null;
    }
}

