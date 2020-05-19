package Controller;

import Model.UserHandler;

public class UserManaging {
    public static void logout() {
        UserHandler.currentUser = null;
        UserHandler.currentBuyer = null;
        UserHandler.currentManager=null;
        UserHandler.currentSeller = null;
    }
    public static String showPersonalInfo(){
        return UserHandler.currentUser.viewUserPersonalInfo();
    }
}
