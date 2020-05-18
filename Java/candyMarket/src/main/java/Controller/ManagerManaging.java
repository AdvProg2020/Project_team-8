package Controller;

import Model.*;

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
        return null;
    }

    public static void editAFieldOfOfInfo(String toBeEditedField, String newField) {
        ;
    }

    public static ArrayList<String> manageUsers() {
        return null;
    }

    public static String viewUser(String username) {
        return null;
    }

    public static void deleteUser(String username) {
        ;
    }

    public static void createNewManager(HashMap<String, String> info) {
        ;
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
        return null;
    }

    public static boolean isThereRequestWithID(int id) {return false;}

    public static String requestDetails(int id) {
        return null;
    }

    public static void acceptRequest(int id) {
        ;
    }

    public static void rejectRequest(int id) {
        ;
    }

    public static ArrayList<String> manageCategories() {
        return null;
    }

    public static boolean isThereSuchCategory(String categoryName) {
        return false;
    }

    public static boolean isThereSuchGoodInCategory(String categoryName, String goodName) {
        return false;
    }

    public static void editCategory(String toBeEditedField, String categoryName, String newField) {
        ;
    }

    public static void addCategory(String name, ArrayList<Good> goods, ArrayList<String> Attributes) {
        ;
    }

    public static void removeCategory(String name) {
        ;
    }

    public static void logout() {

    }
}
