package menu;

import entity.Student;
import menu.util.Input;
import menu.util.Massage;
import service.*;
import util.AuthHolder;

public class Menu {
    private final Input INPUT;
    private final StudentService studentService;


    public Menu(Input input, StudentService studentService) {

        this.INPUT = input;
        this.studentService = studentService;
    }

    public void show() {

        System.out.println("Welcome to my program");
        while (true) {
            System.out.println("Enter your information ");
            System.out.println("Enter username: ");
            String username = INPUT.scanner.next();
            System.out.println("Enter password: ");
            String password = INPUT.scanner.next();

        }

    }

    }



