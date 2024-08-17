package menu;

import menu.util.Input;
import menu.util.Message;
import service.StudentService;
import util.AuthHolder;

public class Signin {
    private final Input input;
    private final Message message;
    private final StudentService studentService;
    private final AuthHolder authHolder;
    private final RegisterLoan registerLoan;


    public Signin(Input input, Message message, StudentService studentService, AuthHolder authHolder, RegisterLoan registerLoan) {
        this.input = input;
        this.message = message;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.registerLoan = registerLoan;
    }

    public void show() {
        StudentLoginMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Register Loan 
                    2- Repayment Menu
                    3- Previous Menu
                    4- Exit
                    """);
            switch (input.scanner.next()) {
                case "1": {
                    registerLoan.show();
                    break;
                }
                case "2": {
                    break;
                }
                case "3": {
                    break StudentLoginMenu;
                }
                case "4": {
                    System.exit(0);
                }
                default:
                    System.out.println(message.getInvalidMassage());
            }

        }
    }
}
