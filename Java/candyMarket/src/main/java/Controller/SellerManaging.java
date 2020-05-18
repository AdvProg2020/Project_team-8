package Controller;

import Model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SellerManaging {
    private static Seller seller;

    public static void setSeller() {
        ;
    }

    public static String viewPersonalInfo() {
        return UserHandler.currentUser.viewUserPersonalInfo();
    }

    public static void editFieldOfInfo(String toBeEditedField,String newField) {
        UserHandler.currentUser.editPersonalInfo(toBeEditedField, newField);
    }

    public static String viewCompaniesInfo() {
        return UserHandler.currentSeller.viewCompanyInformation();
    }

    public static ArrayList<String> viewSalesHistory() {
        return null;
    }

    public static HashMap<Integer, String> manageProducts() {
        return null;
    }

    public static String viewProduct(int id) {
        return null;
    }

    public static ArrayList<String> viewBuyersOfProduct(int id) {
        return null;
    }

    public static boolean editProduct(int id) {
        return false;
    }

    public static void addProduct(String name, String brand, int price, int stock, String category, String categorySpecialAttributes, String detailInfo) {
        (new Request(Request.requestType.CREATE_GOOD)).createAddProductRequest(new Good(name, brand, price, seller, stock, Category.getCategoryByName(category), categorySpecialAttributes, detailInfo));
    }

    public static boolean removeProduct(int id) {
        return false;
    }

    public static ArrayList<String> showCategories() {
        return null;
    }

    public static HashMap<Integer, String> viewOffs() {
        return null;
    }

    public static String viewOff(int id) {
        return null;
    }

    public static boolean editOff(int id) {
        return false;
    }

    public static boolean addOff(ArrayList<String> details) {
        return false;
    }

    public static int viewBalance() {
        return 0;
    }

    public static void logout() {
        UserHandler.currentUser = null;
        UserHandler.currentBuyer = null;
    }
}
