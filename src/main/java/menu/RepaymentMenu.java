package menu;

import entity.CreditCard;
import entity.Installment;
import menu.util.Input;
import menu.util.Message;
import service.CreditCardService;
import service.InstallmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RepaymentMenu {
    private final Input input;
    private final Message message;
    private final InstallmentService installmentService;
    private final CreditCardService creditCardService;


    public RepaymentMenu(Input input, Message message, InstallmentService installmentService, CreditCardService creditCardService) {
        this.input = input;
        this.message = message;

        this.installmentService = installmentService;

        this.creditCardService = creditCardService;
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
                    if (installmentsPayed != null && !installmentsPayed.isEmpty())
                        installmentsPayed.forEach(System.out::println);
                    else System.out.println("Not exit any paying installment");
                    break;
                }
                case "2": {
                    List<Installment> installmentsPayed = installmentService.loadIsPayed(false);
                    if (installmentsPayed != null && !installmentsPayed.isEmpty())
                        installmentsPayed.forEach(System.out::println);
                    else System.out.println("Not exit any dont paying installment");
                    break;
                }
                case "3": {
                    String loanType = getInputData("Which type you want to pay? Educational or Tuition or Housing");
                    String creditCardNumber = getInputData("creditCardNumber");
                    String expirationDateString = getInputData("expirationDate as \'yyyy-MM-dd\'");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate expirationDate = LocalDate.parse(expirationDateString, formatter);
                    String ccv2 = getInputData("ccv2");
                    CreditCard creditCard = creditCardService.findByUniqId(creditCardNumber);
                    CreditCard updateCreditCard = null;
                    if (creditCard != null) {
                        if (!creditCard.getExpirationDate().equals(expirationDate)
                                || !creditCard.getCCV2().equals(ccv2)) {
                            System.out.println(message.getFailMassage("expirationDate or ccv2 not correct!"));
                            break;
                        }
                        if (expirationDate.isBefore(LocalDate.now())) {
                            System.out.println("Card is expired do want enter true expirationDate? (yes/no)");
                            if (input.scanner.next().equals("yes")) {
                                expirationDateString = getInputData("expirationDate as \'yyyy-MM-dd\'");
                                expirationDate = LocalDate.parse(expirationDateString, formatter);
                                creditCard.setExpirationDate(expirationDate);
                                updateCreditCard = creditCardService.update(creditCard);


                            }
                            break;
                        }
                    } else
                        System.out.println(message.getFailMassage("credit card not exist"));
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
