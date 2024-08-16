package menu;

import menu.util.Input;
import menu.util.Message;

public class Menu {
    private final Input INPUT;
    private final Message massage;
    private final Signup signup;
    private final Signin signin;


    public Menu(Input input, Message massage, Signup signup, Signin signin) {

        this.INPUT = input;
        this.massage = massage;
        this.signup = signup;
        this.signin = signin;
    }

    public void show() {

        System.out.println("Welcome to student_pay_loan_repayment");
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Sign up  
                    2- Sign in 
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    signup.show();
                    break;
                }
                case "2": {
                    signin.show();
                    break;
                }
                default:
                    System.out.println(massage.getInvalidMassage());
            }
        }

    }

}



