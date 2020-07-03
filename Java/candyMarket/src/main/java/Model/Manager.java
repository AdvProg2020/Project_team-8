package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends User {
    public static Manager currentManager;

    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.MANAGER);
        ManageInfo.allManagers.add(this);
        ManageInfo.allUsers.add(this);
    }

    public static boolean isThisTheFirstManager() {
        if(ManageInfo.allManagers.isEmpty())
            return true;
        return false;
    }


    public ArrayList<String> viewAllGoods() {
        return null;
    }

    public void removeGood(int goodId) {

    }

    public void createDiscountCode() {

    }

    private void setUsersWhoGetDiscount(Discount discount) {

    }


}