package menu;

import entity.Student;
import menu.util.Input;
import menu.util.Message;
import service.StudentService;
import util.AuthHolder;

public class Menu {
    private final Input input;
    private final Message message;
    private final Signup signup;
    private final Signin signin;
    private final StudentService studentService;
    private final AuthHolder authHolder;


    public Menu(Input input, Message message, Signup signup, Signin signin, StudentService studentService, AuthHolder authHolder) {

        this.input = input;
        this.message = message;
        this.signup = signup;
        this.signin = signin;
        this.studentService = studentService;
        this.authHolder = authHolder;
    }

    public void show() {

        System.out.println("Welcome to student_pay_loan_repayment");
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Sign up  
                    2- Sign in 
                    """);
            switch (input.scanner.next()) {
                case "1": {
                    signup.show();
                    break;
                }
                case "2": {
                    System.out.println(message.getInputMassage("username"));
                    String username = input.scanner.next();
                    System.out.println(message.getInputMassage("password"));
                    String password = input.scanner.next();
                    Student studentLogin = studentService.login(username, password);
                    if (studentLogin != null) {
                        authHolder.tokenId = studentLogin.getId();
                        authHolder.tokenName = studentLogin.getUsername();
                        signin.show();
                    }else System.out.println(message.getFailMassage("Error in login"));
                    break;
                }
                default:
                    System.out.println(message.getInvalidMassage());
            }
        }

    }

}



