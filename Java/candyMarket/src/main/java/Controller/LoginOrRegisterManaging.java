package Controller;


import java.util.ArrayList;
import java.util.HashMap;

public class LoginOrRegisterManaging {

    public static boolean isThereUsernameWithThisName(String requestedUsername) {
        return true;
    }

    public static boolean isThisTheFirstManager() {
        return false;
    }

    public static boolean isThereASameEmail(String requestedMail) {
        return true;
    }

    public static String register(HashMap<String, String> info) {
        return null;
    }

    public static int login(String username, String password) {
        //1.Buyer
        //2.Seller
        //3.Manager
        //4.WrongPassword
        return 0;
    }
}