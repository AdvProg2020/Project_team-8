package Controller;

import Model.Discount;
import Model.Manager;
import Model.Request;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerManaging {
    private static Manager manager;
    private static Scanner scanner;

    public static void setManager() {
        ;
    }

    public static void setScanner(Scanner scanner) {
        ManagerManaging.scanner = scanner;
    }

    public String showPersonalInfo(){
        return null;
    }

    public boolean editAFieldOfOfInfo() {
        return false;
    }

    public HashMap<Integer, String> manageUsers() {
        return null;
    }

    public User viewUser() {
        return null;
    }

    public boolean deleteUser() {
        return false;
    }

    public boolean createNewManager() {
        return false;
    }

    public HashMap<Integer, String> manageAllProducts() {
        return null;
    }

    public boolean removeProduct() {
        return false;
    }

    public boolean createDiscountCode() {
        return false;
    }

    public ArrayList<String> viewDiscountCodes() {
        return null;
    }

    public Discount viewDiscountCode() {
        return null;
    }

    public boolean editDiscountCode() {
        return false;
    }

    public boolean removeDiscountCode() {
        return false;
    }

    public HashMap<Integer, String> manageRequests() {
        return null;
    }

    public Request requestDetails() {
        return null;
    }

    public boolean acceptRequest() {
        return false;
    }

    public boolean rejectRequest() {
        return false;
    }

    public ArrayList<String> manageCategories() {
        return null;
    }

    public boolean editCategory() {
        return false;
    }

    public boolean addCategory() {
        return false;
    }

    public boolean removeCategory() {
        return false;
    }
}
