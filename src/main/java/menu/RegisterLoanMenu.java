package menu;

import entity.Bank;
import entity.CreditCard;
import entity.Student;
import entity.Term;
import entity.loan.EducationLoan;
import exception.ValidationException;
import menu.util.Input;
import menu.util.Message;
import service.*;
import util.AuthHolder;

import java.time.LocalDate;
import java.util.List;

public class RegisterLoanMenu {
    private final Input input;
    private final Message message;
    private final StudentService studentService;
    private final AuthHolder authHolder;
    private final LoanService loanService;
    private final TermService termService;
    private final EducationLoanService educationLoanService;
    private final BankService bankService;
    private final CreditCardService creditCardService;


    public RegisterLoanMenu(Input input, Message message, StudentService studentService, AuthHolder authHolder, LoanService loanService, TermService termService, EducationLoanService educationLoanService, BankService bankService, CreditCardService creditCardService) {
        this.input = input;
        this.message = message;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.loanService = loanService;
        this.termService = termService;
        this.educationLoanService = educationLoanService;
        this.bankService = bankService;
        this.creditCardService = creditCardService;
    }

    public void show() {
        LocalDate localDateNow = LocalDate.of(1403, 8, 3);
        //                    LocalDate.now()
        Boolean isAppropriateDate = loanService.isAppropriateDate(localDateNow);
        if (!isAppropriateDate) System.out.println("LoanPay Service is available only 08-01/08-07 and 11-25/12-02 ");
        RegisterLoanMenu:
        while (isAppropriateDate) {
            String titleTerm = loanService.convertDateToTitleTerm(localDateNow);
            Term currentTerm = termService.findByUniqId(titleTerm);
            Student student = studentService.findByUsername(authHolder.tokenName);
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
                    try {
                        Boolean validGetLoan = educationLoanService.isValidGetLoan(localDateNow);
                        if (validGetLoan) {
                            List<Bank> banks = bankService.loadAll();
                            System.out.println("Bank List:");
                            if (!banks.isEmpty()) banks.forEach(System.out::println);
                            String bankName = getInputData("bankName");
                            List<Bank> bankList = banks.stream()
                                    .filter(a -> a.getName().equals(bankName))
                                    .toList();
                            if (bankList.get(0) != null) {
                                String creditCardNumber = getInputData("creditCardNumber");
                                CreditCard creditCard = creditCardService.save(
                                        CreditCard.builder().creditCardNumber(creditCardNumber).bank(
                                                bankList.get(0)
                                        ).build()
                                );
                                if (creditCard != null) {
                                    EducationLoan educationLoan = educationLoanService.save(
                                            EducationLoan.builder()
                                                    .term(currentTerm)
                                                    .student(student)
                                                    .amount(educationLoanService
                                                            .loanAmount(student))
                                                    .build()
                                    );
                                    if (educationLoan != null)
                                        System.out.println(message.getSuccessfulMassage(authHolder.getTokenName()));
                                }

                            } else System.out.println("Please enter bank name from above list ");
                        } else System.out.println("You are not validate for loan");
                    } catch (ValidationException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
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

    public String getInputData(String prompt) {
        System.out.println(message.getInputMassage(prompt));
        return input.scanner.next();
    }

}
