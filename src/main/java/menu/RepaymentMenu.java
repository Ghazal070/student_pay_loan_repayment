package menu;

import entity.CreditCard;
import entity.Installment;
import entity.loan.Loan;
import menu.util.Input;
import menu.util.Message;
import service.CreditCardService;
import service.InstallmentService;
import service.LoanService;
import util.AuthHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepaymentMenu {
    private final Input input;
    private final Message message;
    private final InstallmentService installmentService;
    private final CreditCardService creditCardService;
    private final LoanService loanService;
    private final AuthHolder authHolder;


    public RepaymentMenu(Input input, Message message, InstallmentService installmentService, CreditCardService creditCardService, LoanService loanService, AuthHolder authHolder) {
        this.input = input;
        this.message = message;

        this.installmentService = installmentService;

        this.creditCardService = creditCardService;
        this.loanService = loanService;
        this.authHolder = authHolder;
    }

    public void show() {
        repaymentLoop:
        while (true) {
            System.out.println("""
                    1- Paid installments
                    2- Unpaid installments
                    3- Pay installment
                    4- Previous Menu
                    5- Exit
                    """);
            switch (input.scanner.next()) {
                case "1": {
                    List<Installment> installmentsPayed = installmentService.loadIsPayed(true);
                    if (installmentsPayed != null && !installmentsPayed.isEmpty()){
                        installmentsPayed.sort(Comparator.comparing(Installment::getLocalDate));
                        installmentsPayed.forEach(System.out::println);

                    }

                    else System.out.println("Not exit any paying installment");
                    break;
                }
                case "2": {
                    List<Installment> installmentsPayed = installmentService.loadIsPayed(false);
                    if (installmentsPayed != null && !installmentsPayed.isEmpty()){
                        installmentsPayed.sort(Comparator.comparing(Installment::getLocalDate));
                        installmentsPayed.forEach(System.out::println);
                    }

                    else System.out.println("Not exit any dont paying installment");
                    break;
                }
                case "3": {
                    try {
                        String loanType = getInputData("Which type you want to pay? Education or Tuition or Housing");
                        String creditCardNumber = getInputData("creditCardNumber");
                        String expirationDateString = getInputData("expirationDate as \'yyyy-MM-dd\'");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate expirationDate = LocalDate.parse(expirationDateString, formatter);
                        String ccv2 = getInputData("ccv2");
                        CreditCard creditCard = creditCardService.findByUniqId(creditCardNumber);
                        if (creditCard != null) {
                            if (!(loanType.equals("Education") || loanType.equals("Tuition")
                                    || loanType.equals("Housing"))) {
                                System.out.println(message.getFailMassage("Loan Type not Correct please choose from items"));
                                break;
                            } else loanType = loanType + "Loan";
                            if (!creditCard.getExpirationDate().equals(expirationDate)
                                    || !creditCard.getCCV2().equals(ccv2)) {
                                System.out.println(message.getFailMassage("expirationDate or ccv2 not correct!"));
                                break;
                            }
                            LocalDate miladiToShamsi = creditCardService.convertDateMiladiToShamsi(LocalDate.now());
                            if (expirationDate.isBefore(miladiToShamsi)) {
                                System.out.println("Card is expired do want enter true expirationDate? (yes/no)");
                                if (input.scanner.next().equals("yes")) {
                                    expirationDateString = getInputData("expirationDate as \'yyyy-MM-dd\'");
                                    expirationDate = LocalDate.parse(expirationDateString, formatter);
                                    ccv2 = getInputData("ccv2");
                                    creditCard.setExpirationDate(expirationDate);
                                    creditCard.setCCV2(ccv2);
                                    creditCardService.update(creditCard);
                                }
                                break;
                            }
                            List loanTypeStudent = loanService.findByLoanTypeStudent(loanType);
                            List<Installment> allInstallments = new ArrayList<>();
                            if (loanTypeStudent != null && !loanTypeStudent.isEmpty()) {
                                for (int i = 0; i < loanTypeStudent.size(); i++) {
                                    Loan loan = (Loan) loanTypeStudent.get(i);
                                    List<Installment> installments = installmentService.loadAllInstallmentLoanIsPay(loan.getId(), false);
                                    allInstallments.addAll(installments);
                                }
                                if (!allInstallments.isEmpty()) {
                                    allInstallments.sort(Comparator.comparing(Installment::getLocalDate));
                                    Installment installment = allInstallments.get(0);
                                    int diffMountInstallBalanceCard = creditCard.getBalance() - installment.getAmount();
                                    if (diffMountInstallBalanceCard >= 0) {
                                        installment.setPayed(true);
                                        installmentService.update(installment);
                                        creditCard.setBalance(diffMountInstallBalanceCard);
                                        creditCardService.update(creditCard);
                                        System.out.println(message.getSuccessfulMassage(authHolder.tokenName));
                                    } else System.out.println("CreditCard Balance Not Enough!");
                                } else System.out.println("Installment is Empty");
                            } else System.out.println(loanType +" is Empty");


                        } else
                            System.out.println(message.getFailMassage("credit card not exist"));
                    } catch (RuntimeException e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
                    break;
                }
                case "4": {
                    break repaymentLoop;
                }
                case "5": {
                    System.exit(0);
                }
                default:
                    System.out.println(Message.getInvalidMassage());
            }
        }

    }

    public String getInputData(String prompt) {
        System.out.println(message.getInputMassage(prompt));
        return input.scanner.next();
    }
}
