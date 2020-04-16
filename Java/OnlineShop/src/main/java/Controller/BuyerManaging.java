package Controller;

import Model.Buyer;
import Model.Good;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BuyerManaging {
    private static Buyer buyer;
    private static Scanner scanner;

    public static void setBuyer() {
        ;
    }

    public static void setScanner(Scanner scanner) {
        BuyerManaging.scanner = scanner;
    }

    public String showPersonalInfo(){
        return null;
    }

    public boolean editAFieldOfOfInfo() {
        return false;
    }

    public void viewCart() {
        ;
    }

    public HashMap<Integer, String> showProductsFromCart() {
        return null;
    }

    public Good viewProductIdFromCart() {
        return null;
    }

    public int increaseProductNumberInCart() {
        return 0;
    }

    public int decreaseProductNumberInCart() {
        return 0;
    }

    public int showTotalPrice() {
        return 0;
    }

    public void purchase() {
        ;
    }

    public void receiverInfo() {
        ;
    }

    public boolean discountCodeEntry() {
        return false;
    }

    public boolean payment() {
        return false;
    }

    public HashMap<Integer, String> viewOrders() {
        return null;
    }

    public Good showOrder() {
        return null;
    }

    public boolean rateProduct() {
        return false;
    }

    public int viewBalance() {
        return 0;
    }

    public ArrayList<String> viewDiscountCodes() {
        return null;
    }
}
