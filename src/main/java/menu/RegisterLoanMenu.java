package menu;

import entity.Bank;
import entity.CreditCard;
import entity.Student;
import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.HousingLoan;
import entity.loan.TuitionLoan;
import exception.ValidationException;
import menu.util.Input;
import menu.util.Message;
import service.*;
import util.AuthHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final TuitionLoanService tuitionLoanService;
    private final HousingLoanService housingLoanService;
    private final InstallmentService installmentService;


    public RegisterLoanMenu(Input input, Message message, StudentService studentService, AuthHolder authHolder, LoanService loanService, TermService termService, EducationLoanService educationLoanService, BankService bankService, CreditCardService creditCardService, TuitionLoanService tuitionLoanService, HousingLoanService housingLoanService, InstallmentService installmentService) {
        this.input = input;
        this.message = message;
        this.studentService = studentService;
        this.authHolder = authHolder;
        this.loanService = loanService;
        this.termService = termService;
        this.educationLoanService = educationLoanService;
        this.bankService = bankService;
        this.creditCardService = creditCardService;
        this.tuitionLoanService = tuitionLoanService;
        this.housingLoanService = housingLoanService;
        this.installmentService = installmentService;
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
                        Boolean validGetLoan =
                                educationLoanService.isValidGetLoan(localDateNow);
                        if (validGetLoan) {
                            System.out.println("""
                                    1- Register a credit card
                                    2- Use your existing card number
                                    """);
                            CreditCard creditCard = null;
                            switch (input.scanner.next()) {
                                case "1": {
                                    List<Bank> banks = bankService.loadAll();
                                    System.out.println("Bank List:");
                                    if (!banks.isEmpty()) banks.forEach(System.out::println);
                                    String bankName = getInputData("bankName");
                                    List<Bank> bankList = banks.stream()
                                            .filter(a -> a.getName().equals(bankName))
                                            .toList();
                                    if (bankList.get(0) != null) {
                                        String creditCardNumber = getInputData("creditCardNumber");
                                        if (creditCardService.findByUniqId(creditCardNumber) == null) {
                                            String ccv2 = getInputData("CCV2");
                                            String expirationDate = getInputData("expirationDate as \'yyyy-MM-dd\'");
                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                            LocalDate localDate = LocalDate.parse(expirationDate, formatter);
                                            creditCard = creditCardService.save(
                                                    CreditCard.builder().creditCardNumber(creditCardNumber).bank(
                                                                    bankList.get(0)
                                                            )
                                                            .expirationDate(localDate)
                                                            .CCV2(ccv2)
                                                            .balance(0)
                                                            .student(student)
                                                            .build()
                                            );

                                        } else System.out.println("This credit card is exist");
                                    } else System.out.println("Please enter bank name from above list ");
                                    break;
                                }
                                case "2": {
                                    String creditCardNumber = getInputData("creditCardNumber");
                                    creditCard = creditCardService.findByUniqId(creditCardNumber);
                                    if (creditCard == null) System.out.println("credit card not exist");
                                    break;
                                }
                                default:
                                    System.out.println(message.getInvalidMassage());
                            }
                            if (creditCard != null) {
                                Integer loanAmount = educationLoanService.loanAmount(student);
                                EducationLoan educationLoan = educationLoanService.save(
                                        EducationLoan.builder()
                                                .term(currentTerm)
                                                .student(student)
                                                .amount(loanAmount)
                                                .build()
                                );
                                if (educationLoan != null) {
                                    creditCard.setBalance(creditCard.getBalance() + loanAmount);
                                    creditCardService.update(creditCard);
                                    System.out.println(message.getSuccessfulMassage(authHolder.getTokenName()));
                                    boolean installment = installmentService.createInstallment(educationLoan);
                                }

                            }
                        } else System.out.println("You have register for educational loan in this term");
                    } catch (ValidationException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
                    break;
                }
                case "2": {
                    try {
                        Boolean validGetLoan =
                                tuitionLoanService.isValidGetLoan(localDateNow);
                        if (validGetLoan) {
                            System.out.println("""
                                    1- Register a credit card
                                    2- Use your existing card number
                                    """);
                            CreditCard creditCard = null;
                            switch (input.scanner.next()) {
                                case "1": {
                                    List<Bank> banks = bankService.loadAll();
                                    System.out.println("Bank List:");
                                    if (!banks.isEmpty()) banks.forEach(System.out::println);
                                    String bankName = getInputData("bankName");
                                    List<Bank> bankList = banks.stream()
                                            .filter(a -> a.getName().equals(bankName))
                                            .toList();
                                    if (bankList.get(0) != null) {
                                        String creditCardNumber = getInputData("creditCardNumber");

                                        if (creditCardService.findByUniqId(creditCardNumber) == null) {
                                            creditCard = creditCardService.save(
                                                    CreditCard.builder().creditCardNumber(creditCardNumber).bank(
                                                                    bankList.get(0)
                                                            )
                                                            .balance(0)
                                                            .student(student)
                                                            .build()
                                            );

                                        } else System.out.println("This credit card is exist");
                                    } else System.out.println("Please enter bank name from above list ");
                                    break;
                                }
                                case "2": {
                                    String creditCardNumber = getInputData("creditCardNumber");
                                    creditCard = creditCardService.findByUniqId(creditCardNumber);
                                    if (creditCard == null) System.out.println("credit card not exist");
                                    break;
                                }
                                default:
                                    System.out.println(message.getInvalidMassage());
                            }
                            if (creditCard != null) {
                                Integer loanAmount = tuitionLoanService.loanAmount(student);
                                TuitionLoan tuitionLoan = tuitionLoanService.save(
                                        TuitionLoan.builder()
                                                .student(student)
                                                .term(currentTerm)
                                                .amount(loanAmount)
                                                .build()
                                );
                                if (tuitionLoan != null) {
                                    creditCard.setBalance(creditCard.getBalance() + loanAmount);
                                    creditCardService.update(creditCard);
                                    System.out.println(message.getSuccessfulMassage(authHolder.getTokenName()));
                                }

                            }
                        } else System.out.println("You have register for tuition loan in this term");
                    } catch (ValidationException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
                    break;
                }
                case "3": {
                    try {
                        String isMarried = getInputData("isMarried (yes/no)");
                        String partnerNationalCode = getInputData("partnerNationalCode");

                        String address = getInputData("address");
                        String contractNumber = getInputData("contractNumber");
                        student.setIsMarried(getYesNo(isMarried));
                        student.setPartnerNationalCode(partnerNationalCode);
                        student.setAddress(address);
                        student.setContractNumber(contractNumber);
                        Student updateStudent = studentService.update(student);
                        Boolean validGetLoan =
                                housingLoanService.isValidGetLoan(localDateNow);
                        if (validGetLoan) {
                            System.out.println("""
                                    1- Register a credit card
                                    2- Use your existing card number
                                    """);
                            CreditCard creditCard = null;
                            switch (input.scanner.next()) {
                                case "1": {
                                    List<Bank> banks = bankService.loadAll();
                                    System.out.println("Bank List:");
                                    if (!banks.isEmpty()) banks.forEach(System.out::println);
                                    String bankName = getInputData("bankName");
                                    List<Bank> bankList = banks.stream()
                                            .filter(a -> a.getName().equals(bankName))
                                            .toList();
                                    if (bankList.get(0) != null) {
                                        String creditCardNumber = getInputData("creditCardNumber");

                                        if (creditCardService.findByUniqId(creditCardNumber) == null) {
                                            creditCard = creditCardService.save(
                                                    CreditCard.builder().creditCardNumber(creditCardNumber).bank(
                                                                    bankList.get(0)
                                                            )
                                                            .balance(0)
                                                            .student(student)
                                                            .build()
                                            );

                                        } else System.out.println("This credit card is exist");
                                    } else System.out.println("Please enter bank name from above list ");
                                    break;
                                }
                                case "2": {
                                    String creditCardNumber = getInputData("creditCardNumber");
                                    creditCard = creditCardService.findByUniqId(creditCardNumber);
                                    if (creditCard == null) System.out.println("credit card not exist");
                                    break;
                                }
                                default:
                                    System.out.println(message.getInvalidMassage());
                            }
                            if (creditCard != null) {
                                Integer loanAmount = housingLoanService.loanAmount(updateStudent);
                                HousingLoan housingLoan = housingLoanService.save(
                                        HousingLoan.builder()
                                                .student(student)
                                                .amount(loanAmount)
                                                .build()
                                );
                                if (housingLoan != null) {
                                    creditCard.setBalance(creditCard.getBalance() + loanAmount);
                                    creditCardService.update(creditCard);
                                    System.out.println(message.getSuccessfulMassage(authHolder.getTokenName()));
                                }

                            }
                        } else System.out.println("You have register for housing loan in this degree");
                    } catch (ValidationException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
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

    private Boolean getYesNo(String prompt) {
        if (prompt.equals("yes")) return true;
        return false;
    }

}
