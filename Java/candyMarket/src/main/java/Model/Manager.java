package Model;

import java.util.ArrayList;

public class Manager extends User {
    public static Manager currentManager;

    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.MANAGER);
    }

    public static boolean isThisTheFirstManager() {
        int managerCounter = 0;
        for (User user : User.users) {
            if (user.getType().equals(UserType.MANAGER))
                managerCounter++;
        }
        if (managerCounter == 0)
            return true;
        return false;
    }

    public static ArrayList<String> viewAllUsers() {
        ArrayList<String> allUsers = new ArrayList<>();
        for (User user : User.users) {
            allUsers.add(user.getUsername());
        }
        return allUsers;
    }

    public static String viewUser(String username) {
        return User.getUserByUsername(username).viewUserPersonalInfo();
    }

    public static void deleteUser(String username) {
        User.users.remove(User.getUserByUsername(username));
    }
    public void createNewManager() {

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

    public ArrayList<String> viewAllDiscountCodes() {
        return null;
    }

    public ArrayList<String> viewDiscountCode(String code) {
        return null;
    }

    public void editDiscountCode(String code) {

    }

    public void removeDiscountCode(String code) {

    }

    public ArrayList<String> viewAllRequests() {
        return null;
    }

    public ArrayList<String> viewRequestDetails(Request request) {
        return null;
    }

    public boolean replyRequest(Request request) {
        return true;
    }

    public ArrayList<String> viewAllCategories() {
        return null;
    }

    public void editCategory(Category category) {

    }

    public void addCategory() {

    }

    public void removeCategory(Category category) {

    }


}
