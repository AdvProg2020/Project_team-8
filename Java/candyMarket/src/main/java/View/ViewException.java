package View;

public class ViewException extends Exception {
    public static ViewException invalidNumber(){
        return new ViewException("please enter valid number");
    }
    public static ViewException cantComment(){
        return new ViewException("you should buy the good at first");
    }
    public static ViewException invalidEmailFormat(){
        return new ViewException("Please enter valid mail");
    }
    public static ViewException invalidPhoneNumberFormat(){
        return new ViewException("Please enter valid phone number");
    }
    public ViewException (String msg){
        super(ConsoleDesign.RED+"Error : "+ ConsoleDesign.WHITE+msg);
    }
}
