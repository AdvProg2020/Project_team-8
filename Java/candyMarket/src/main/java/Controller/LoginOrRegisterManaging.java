package Controller;


import Model.Buyer;
import Model.Manager;
import Model.Seller;
import Model.User;

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

    public static void register(HashMap<String, String> info) {
        switch (info.get("type")) {
            case "seller":
                new Seller(info.get("username"), info.get("firstName"), info.get("lastName"), info.get("email"), info.get("phoneNumber"), info.get("password"), info.get("companyName"));
                break;
            case "buyer":
                new Buyer(info.get("username"), info.get("firstName"), info.get("lastName"), info.get("email"), info.get("phoneNumber"), info.get("password"));
                break;
            case "manager":
                new Manager(info.get("username"), info.get("firstName"), info.get("lastName"), info.get("email"), info.get("phoneNumber"), info.get("password"));
                break;
        }
    }

    public static int login(String username, String password) {
        User currentUser = User.getUserByUsername(username);
        if (currentUser.isUsernameAndPasswordCorrect(username, password)) {
            switch (currentUser.getUsername()) {
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
