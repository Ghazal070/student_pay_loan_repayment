package menu;

import entity.Term;
import menu.util.Input;
import menu.util.Message;
import service.EducationLoanService;
import service.LoanService;
import service.StudentService;
import service.TermService;
import util.AuthHolder;

import java.time.LocalDate;

public class RegisterLoanMenu {
    private final Input input;
    private final Message message;
    private final StudentService studentService;
    private final AuthHolder authHolder;
    private final LoanService loanService;
    private final TermService termService;
    private final EducationLoanService educationLoanService;


    public RegisterLoanMenu(Input input, Message message, StudentService studentService, AuthHolder authHolder, LoanService loanService, TermService termService, EducationLoanService educationLoanService) {
        this.input = input;
        this.message = message;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.loanService = loanService;
        this.termService = termService;
        this.educationLoanService = educationLoanService;
    }

    public void show() {
        LocalDate localDateNow = LocalDate.of(1403, 8, 3);
        //                    LocalDate.now()
        Boolean isAppropriateDate = loanService.isAppropriateDate(localDateNow);

        if (!isAppropriateDate) System.out.println("LoanPay Service is available only 08-01/08-07 and 11-25/12-02 ");
        RegisterLoanMenu:
        while (isAppropriateDate) {

            System.out.println("""
                    Enter one of the following options
                    1- Register EducationLoan
                    2- Register TuitionLoan
                    3- Register HousingLoan
                    4- Previous Menu
                    5- Exit
                    """);
            switch (input.scanner.next()) {
                case "1": {
                    Boolean validGetLoan = educationLoanService.isValidGetLoan(localDateNow);
                    if(validGetLoan){
                        System.out.println();//todo shomareKart
                        System.out.println(message.getSuccessfulMassage(authHolder.getTokenName()));
                    }
                    break;
                }
                case "2": {
                    break;
                }
                case "3": {
                    break;
                }
                case "4": {
                    break RegisterLoanMenu;
                }
                case "5": {
                    System.exit(0);
                }
                default:
                    System.out.println(message.getInvalidMassage());
            }


        }
    }

    private Term getCurrentTerm() {
        System.out.println(message.getInputMassage(" current title term"));
        String titleTerm = input.scanner.next();
        Term term = termService.findByUniqId(titleTerm);
        return term;
    }
}
