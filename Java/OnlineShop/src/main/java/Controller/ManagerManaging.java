package Controller;

import Model.Discount;
import Model.Request;
import Model.User;
import Model.Manager;

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

    public static String showPersonalInfo(){
        return null;
    }

    public static boolean editAFieldOfOfInfo() {
        return false;
    }

    public static HashMap<Integer, String> manageUsers() {
        return null;
    }

    public static User viewUser() {
        return null;
    }

    public static boolean deleteUser() {
        return false;
    }

    public static boolean createNewManager() {
        return false;
    }

    public static HashMap<Integer, String> manageAllProducts() {
        return null;
    }

    public static boolean removeProduct() {
        return false;
    }

    public static boolean createDiscountCode() {
        return false;
    }

    public static ArrayList<String> viewDiscountCodes() {
        return null;
    }

    public static Discount viewDiscountCode() {
        return null;
    }

    public static boolean editDiscountCode() {
        return false;
    }

    public static boolean removeDiscountCode() {
        return false;
    }

    public static HashMap<Integer, String> manageRequests() {
        return null;
    }

    public static Request requestDetails() {
        return null;
    }

    public static boolean acceptRequest() {
        return false;
    }

    public static boolean rejectRequest() {
        return false;
    }

    public static ArrayList<String> manageCategories() {
        return null;
    }

    public static boolean editCategory() {
        return false;
    }

    public static boolean addCategory() {
        return false;
    }

    public static boolean removeCategory() {
        return false;
    }
}
