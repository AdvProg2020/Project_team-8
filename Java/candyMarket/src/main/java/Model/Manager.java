package Model;

import java.util.ArrayList;

public class Manager extends User {
    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, UserType type) {
        super(userName, firstName, lastName, email, phoneNumber, passWord, type);
    }
    public static Manager currentManager;
    public ArrayList<String> viewAllUsers() {
        return null;
    }

    public ArrayList<String> viewUser(String username) {
        return null;
    }

    public void deleteUser(String username) {

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
