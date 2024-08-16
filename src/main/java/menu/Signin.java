package menu;

import entity.*;
import menu.util.Input;
import menu.util.Message;
import service.StudentService;
import util.AuthHolder;

public class Signin {
    private final Input input;
    private final Message message;
    private final StudentService studentService;
    private final AuthHolder authHolder;


    public Signin(Input input, Message message, StudentService studentService, AuthHolder authHolder) {
        this.input = input;
        this.message = message;
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
            switch (input.scanner.next()) {
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
                    System.out.println(message.getInvalidMassage());
            }

        }
    }
}
