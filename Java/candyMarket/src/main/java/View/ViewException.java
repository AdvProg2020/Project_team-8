package View;

public class ViewException extends Exception {
    public static ViewException invalidNumber(){
        return new ViewException("please enter valid number");
    }
    public static ViewException invalidEmailFormat(){
        return new ViewException("Please enter valid mail");
    }
    public ViewException (String msg){
        super(ConsoleDesign.RED+"Error : "+ ConsoleDesign.WHITE+msg);
    }
}
