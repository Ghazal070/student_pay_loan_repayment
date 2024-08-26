package menu;

import entity.Installment;
import menu.util.Input;
import menu.util.Message;

import java.util.List;

public class PayInstallmentMenu {

    private final Input input;
    private final Message message;

    public PayInstallmentMenu(Input input, Message message) {
        this.input = input;
        this.message = message;
    }

    public void show(){
        payInstallment:
        while (true){
            System.out.println("""
                    Choose loan you want to pay
                    1- Educational loan paying
                    2- Tuition loan paying
                    3- Housing loan paying
                    4- Previous Menu
                    5- Exit
                    """);
            switch (input.scanner.next()){
                case "1":{
                    break;
                }
                case "2":{

                    break;
                }
                case "3":{

                    break;
                }
                case "4":{
                    break payInstallment;
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
