package menu;

import menu.util.Input;
import menu.util.Message;
import service.LoanService;
import service.StudentService;
import util.AuthHolder;

import java.time.LocalDate;

public class Signin {
    private final Input input;
    private final Message message;
    private final StudentService studentService;
    private final AuthHolder authHolder;
    private final RegisterLoanMenu registerLoanMenu;


    public Signin(Input input, Message message, StudentService studentService, AuthHolder authHolder, RegisterLoanMenu registerLoanMenu) {
        this.input = input;
        this.message = message;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.registerLoanMenu = registerLoanMenu;
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
                    LocalDate localDateNow = LocalDate.of(1403, 8, 3);
                    //                    LocalDate.now()

                    if (!isAppropriateDate) System.out.println("LoanPay Service is available only 08-01/08-07 and 11-25/12-02 ");

                    registerLoanMenu.show();
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
