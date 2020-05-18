package Controller;

import Model.*;

import javax.print.attribute.standard.MediaName;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerManaging {
    private static Manager manager;

    public static void setManager() {
        ;
    }

    public static String showPersonalInfo(){
        return UserHandler.currentManager.viewUserPersonalInfo();
    }

    public static void editAFieldOfOfInfo(String toBeEditedField, String newField) {
        UserHandler.currentManager.editPersonalInfo(toBeEditedField, newField);
    }

    public static ArrayList<String> showAllUsers() {
        return Manager.viewAllUsers();
    }

    public static String viewUser(String username) {
        return Manager.viewUser(username);
    }

    public static void deleteUser(String username) {
        Manager.deleteUser(username);
    }

    public static HashMap<Integer, String> manageAllProducts() {
        return null;
    }

    public static boolean isThereProductWithId(int id) {
        return false;
    }

    public static void removeProduct(int id) {
        ;
    }

    public static boolean isThisCodeExists(int code) {
        return false;
    }

    public static void createDiscountCode(int code, long start, long end, int percentage, int max, ArrayList<String> users) {
        ;
    }

    public static ArrayList<String> viewDiscountCodes() {
        return null;
    }

    public static String viewDiscountCode(int code) {
        return null;
    }

    public static void editDiscountCodeDate(String toBeEditedField, long newDate) {
        ;
    }

    public static void editDiscountCodeIntegers(String toBeEditedField, int newDate) {
        ;
    }

    public static boolean doesThisPersonHaveThisCode(int code, String username) {
        return true;
    }

    public static void addTheCodeToUser(int code, String username) {
        ;
    }

    public static void removeDiscountCode(int code) {
        ;
    }

    public static HashMap<Integer, String> manageRequests() {
        return Manager.viewAllRequests();
    }

    public static boolean isThereRequestWithID(int id) {
        return Request.isThereRequestWithId(id);
    }

    public static String requestDetails(int id) {
        return Request.viewDetails(Request.getRequestById(id));
    }

    public static void acceptRequest(int id) {
        Request.getRequestById(id).acceptRequest();
    }

    public static void rejectRequest(int id) {
        Request.getRequestById(id).declineRequest();
    }

    public static ArrayList<String> manageCategories() {
        return Manager.viewAllCategories();
    }

    public static boolean isThereSuchCategory(String categoryName) {
        return Category.isThisCategoryExist(categoryName);
    }

    public static boolean isThereSuchGoodInCategory(String categoryName, String goodName) {
        return false;
    }

    public static void editCategory(String toBeEditedField, String categoryName, String newField) {
        ;
    }

    public static void addCategory(String categoryName, String subCategoryOf, ArrayList<String> note) {
        ;
    }

    public static void removeCategory(String name) {
        ;
    }

    public static void logout() {
        UserHandler.currentUser = null;
        UserHandler.currentManager = null;
    }
}