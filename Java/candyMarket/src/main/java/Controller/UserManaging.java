package Controller;

import Model.UserHandler;

public class UserManaging {
    public static void logout() {
        UserHandler.setCurrentUser(null);
        UserHandler.currentBuyer = null;
        UserHandler.currentManager=null;
        UserHandler.currentSeller = null;
    }
    public static String showPersonalInfo(){
        return UserHandler.getCurrentUser().viewUserPersonalInfo();
    }
}
