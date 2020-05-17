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
    public static ViewException existingUsername(){
        return new ViewException("This username has already been taken, please choose another :");
    }
    public static ViewException existingManager(){
        return new ViewException("you can only choose between buyer or seller, please try again :");
    }
    public static ViewException notExistingUsername(){
        return new ViewException("There is no such username in database, please try again :");
    }
    public static ViewException existingEmail(){
        return new ViewException("This email has already been taken, please choose another :");
    }
    public static ViewException invalidPhoneNumberFormat(){
        return new ViewException("Please enter valid phone number");
    }
    public static ViewException invalidPassword(){
        return new ViewException("Password is Wrong, please try again :");
    }
    public ViewException (String msg){
        super(ConsoleDesign.RED+"Error : "+ ConsoleDesign.WHITE+msg);
    }
}
