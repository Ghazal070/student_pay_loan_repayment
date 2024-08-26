package menu;

import entity.Installment;
import menu.util.Input;
import menu.util.Message;
import service.InstallmentService;

import java.util.List;

public class RepaymentMenu {
    private final Input input;
    private final Message message;
    private final InstallmentService installmentService;
    private final PayInstallmentMenu payInstallment;



    public RepaymentMenu(Input input, Message message, InstallmentService installmentService, PayInstallmentMenu payInstallment) {
        this.input = input;
        this.message = message;

        this.installmentService = installmentService;
        this.payInstallment = payInstallment;
    }

    public void show() {
        repaymentLoop:
        while (true){
            System.out.println("""
                    1- Paid installments
                    2- Unpaid installments
                    3- Pay installment
                    4- Previous Menu
                    5- Exit
                    """);
            switch (input.scanner.next()){
                case "1":{
                    List<Installment> installmentsPayed = installmentService.loadIsPayed(true);
                    if (installmentsPayed!=null && !installmentsPayed.isEmpty())
                        installmentsPayed.forEach(System.out::println);
                    else System.out.println("Not exit any paying installment");
                    break;
                }
                case "2":{
                    List<Installment> installmentsPayed = installmentService.loadIsPayed(false);
                    if (installmentsPayed!=null && !installmentsPayed.isEmpty())
                        installmentsPayed.forEach(System.out::println);
                    else System.out.println("Not exit any dont paying installment");
                    break;
                }
                case "3":{
                    payInstallment.show();
                    break;
                }
                case "4":{
                    break repaymentLoop;
                }
                case "5":{
                    System.exit(0);
                }
                default:
                    System.out.println(Message.getInvalidMassage());
            }
        }

    }
}
