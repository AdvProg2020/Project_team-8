package Controller;


import Model.*;

import java.util.HashMap;

public class LoginOrRegisterManaging {

    public static boolean isThereUsernameWithThisName(String requestedUsername) {
        return User.isThereUserWithUsername(requestedUsername);
    }

    public static boolean isThisTheFirstManager() {
        return Manager.isThisTheFirstManager();
    }

    public static boolean isThereASameEmail(String requestedMail) {
        return User.isThereUserWithEmail(requestedMail);
    }

    public static void register(HashMap<String, String> info) {
        switch (info.get("type")) {
            case "seller":
                new Seller(info.get("username"), info.get("firstName"), info.get("lastName"), info.get("email"), info.get("phoneNumber"), info.get("password"), info.get("companyName"), info.get("workType"));
                (new Request(Request.requestType.SELLER_REGISTER)).createRegisterSellerRequest((Seller) User.getUserByUsername(info.get("username")));
                break;
            case "buyer":
                new Buyer(info.get("username"), info.get("firstName"), info.get("lastName"), info.get("email"), info.get("phoneNumber"), info.get("password"));
                break;
            case "manager":
                new Manager(info.get("username"), info.get("firstName"), info.get("lastName"), info.get("email"), info.get("phoneNumber"), info.get("password"));
                break;
        }
        login(info.get("username"), info.get("password"));
    }

    public static int login(String username, String password) {
        User currentUser = User.getUserByUsername(username);
        if (currentUser.isUsernameAndPasswordCorrect(username, password)) {
            switch (currentUser.getType()) {
                case BUYER:
                    Buyer.currentBuyer = (Buyer) User.getUserByUsername(username);
                    return 1;
                case SELLER:
                    Seller.currentSeller = (Seller) User.getUserByUsername(username);
                    return 2;
                case MANAGER:
                    Manager.currentManager = (Manager) User.getUserByUsername(username);
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