package menu;

import entity.*;
import menu.util.Input;
import menu.util.Massage;
import service.StudentService;
import util.AuthHolder;

import java.util.List;
import java.util.Set;

public class StudentLogin {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final StudentService studentService;
    private final AuthHolder authHolder;


    public StudentLogin(Input INPUT, Massage MASSAGE, StudentService studentService, AuthHolder authHolder) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.studentService = studentService;
        this.authHolder = authHolder;
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
                    break;
                }
                case "3": {
                    break;
                }
                case "4": {

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
