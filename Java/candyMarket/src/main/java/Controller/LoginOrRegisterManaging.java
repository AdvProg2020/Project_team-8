package Controller;


import Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginOrRegisterManaging {

    public static boolean isThereUsernameWithThisName(String requestedUsername) {
        return User.isThereUserWithUsername(requestedUsername);
    }

    public static boolean isThisTheFirstManager() {
        return false;
    }

    public static boolean isThereASameEmail(String requestedMail) {
        return User.isThereUserWithEmail(requestedMail);
    }

    public static String register(HashMap<String, String> info) {
        return null;
    }

    public static int login(String username, String password) {
        User currentUser = User.getUserByUsername(username);
        if (currentUser.isUsernameAndPasswordCorrect(username, password)) {
            switch (currentUser.getUserName()) {
                case "buyer":
                    return 1;
                case "seller":
                    return 2;
                case "manager":
                    return 3;
            }
        }
        return 4;
        //1.Buyer
        //2.Seller
        //3.Manager
        //4.WrongPassword
    }
}
