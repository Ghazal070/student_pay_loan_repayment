package menu.util;

public class Message {
    private static final String invalidMassage = "Invalid input, Please enter number of desired item";
    private static final String inputMassage = "Please enter %s ";
    private static final String successfulMassage = "Dear %s operation is successful";
    private static final String ExistMessage = "%s is exist, Please choose different username";
    private static final String inCorrectMessage = "Username or password is incorrect";
    private static final String failMessage = "Sorry! %s fail...";
    private static final String payMessage = "Dear %s your salary is ";
    private static final String priceIncorrect = "Your input price incorrect ,please enter again";
    private static final String creditCardIncorrect = "Your credit card is incorrect";
    private static final String  errorCredit= "Sorry! origin credit lower than transfer amount.";

    public static String getInvalidMassage() {
        return invalidMassage;
    }

    public String getInputMassage(String input) {
        return String.format(inputMassage,input);
    }
    public String getSuccessfulMassage(String input) {
        return String.format(successfulMassage,input);
    }
    public String getExistMassage(String input) {
        return String.format(ExistMessage,input);
    }
    public String getFailMassage(String input) {
        return String.format(failMessage,input);
    }
    public String getPayMassage(String input) {
        return String.format(payMessage,input);
    }
    public String getInCorrectMessage() {
        return inCorrectMessage;
    }
    public String getPriceIncorrect() {
        return priceIncorrect;
    }
    public String getErrorCredit() {
        return errorCredit;
    }
    public String getCreditCardIncorrect() {
        return creditCardIncorrect;
    }
}
