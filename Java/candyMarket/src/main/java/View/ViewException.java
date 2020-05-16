package View;

public class ViewException extends Exception {
    public static ViewException cantComment(){
        return new ViewException("you should buy the good at first");
    }
    public static ViewException invalidNumber(){
        return new ViewException("please enter valid number");
    }
    public ViewException (String msg){
        super(ConsoleDesign.RED+"Error : "+ ConsoleDesign.WHITE+msg);
    }
}
