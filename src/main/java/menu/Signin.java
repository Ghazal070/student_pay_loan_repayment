package menu;

import menu.util.Input;
import menu.util.Message;
import service.StudentService;
import util.AuthHolder;

import java.time.LocalDate;

public class Signin {
    private final Input input;
    private final Message message;
    private final StudentService studentService;
    private final AuthHolder authHolder;
    private final RegisterLoanMenu registerLoanMenu;
    private final RepaymentMenu repaymentMenu;


    public Signin(Input input, Message message, StudentService studentService, AuthHolder authHolder, RegisterLoanMenu registerLoanMenu, RepaymentMenu repaymentMenu) {
        this.input = input;
        this.message = message;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.registerLoanMenu = registerLoanMenu;
        this.repaymentMenu = repaymentMenu;
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
                    LocalDate localDateNow = LocalDate.of(1402, 8, 3);
                    //                    LocalDate.now()

                    if (!studentService.isGraduated(localDateNow))
                        registerLoanMenu.show();
                    else System.out.println("You are graduated.Dont have assess. ");
                    break;
                }
                case "2": {
                    LocalDate localDateNow = LocalDate.of(1403, 8, 3);
                    //                    LocalDate.now()

                    if (studentService.isGraduated(localDateNow))
                        repaymentMenu.show();
                    else System.out.println("You are student.Dont have assess. ");
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
