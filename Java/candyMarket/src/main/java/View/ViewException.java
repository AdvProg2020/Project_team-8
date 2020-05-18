package View;

import Model.ManageInfo;

public class ViewException extends Exception {
    public static ViewException notLogedIn(){
        return new ViewException("You should login for pay your cart");
    }
    public static ViewException outOfStock(){
        return new ViewException("There is no more of this good");
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
    public static ViewException existingUsername(){
        return new ViewException("This username has already been taken, please choose another :");
    }
    public static ViewException existingManager(){
        return new ViewException("you can only choose between buyer or seller, please try again :");
    }
    public static ViewException existingCategory(){
        return new ViewException("This category already exists, please try again :");
    }
    public static ViewException existingGoodInCategory(){
        return new ViewException("This category already has this product, please try again :");
    }
    public static ViewException notExistingUsername(){
        return new ViewException("There is no such username in database, please try again :");
    }
    public static ViewException notExistingCategory(){
        return new ViewException("There is no such category in database, please try again :");
    }
    public static ViewException existingEmail(){
        return new ViewException("This email has already been taken, please choose another :");
    }
    public static ViewException existingDiscountCode(){
        return new ViewException("This code already exists, please enter another :");
    }
    public static ViewException existingDiscountCodeForThisUser(){
        return new ViewException("This user already has the code, please enter another :");
    }
    public static ViewException notExistingDiscountCode(){
        return new ViewException("There is no such code, please enter another :");
    }
    public static ViewException invalidPhoneNumberFormat(){
        return new ViewException("Please enter valid phone number");
    }
    public static ViewException invalidLogin(){
        return new ViewException("Username or Password is Wrong, please try again :");
    }
    public static ViewException invalidIDNumber(){
        return new ViewException("Invalid id number, please try again :");
    }
    public static ViewException invalidDiscountPercentage(){
        return new ViewException("Percentage must be between 0 and 100, please try again :");
    }

    public ViewException (String msg){
        super(ConsoleDesign.RED+"Error : "+ ConsoleDesign.WHITE+msg);
    }
}
