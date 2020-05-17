package View;

public class ViewException extends Exception {
    public static ViewException insufficientFunds(){
        return new ViewException("Insufficient funds");
    }
    public static ViewException cantComment(){
        return new ViewException("you should buy the good at first");
    }
    public static ViewException invalidNumber(){
        return new ViewException("please enter valid number");
    }
    public static ViewException invalidEmailFormat(){
        return new ViewException("Please enter valid mail");
    }
    public static ViewException invalidDiscountCode(){
        return new ViewException("Please enter valid code");
    }

    public ViewException (String msg){
        super(ConsoleDesign.RED+"Error : "+ ConsoleDesign.WHITE+msg);
    }
}
