package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BuyerManaging {
    private static Buyer buyer;

    public static void setBuyer() {
        ;
    }

    public static String showPersonalInfo(){
        return UserHandler.getCurrentUser().viewUserPersonalInfo();
    }

    public static void editAFieldOfOfInfo(String toBeEditedField, String newField) {
        ;
    }

    public static boolean isBuyerBoughtProductWithId(int id) {
        return false;
    }

    public static String ShowOrder(int id) {
        BuyLog buyLog = UserHandler.currentBuyer.getBuyLogById(id);
        if(buyLog == null) return null;
        return buyLog.toString();
    }

    public static ArrayList<String> viewOrders()
    {
        ArrayList<String> show = new ArrayList<>();
        for (BuyLog b:
             UserHandler.currentBuyer.getMyLogs()) {
            show.add(b.getId()+1+" : "+b.getTotalAmount());
        }
        return show;
    }
    public static ArrayList<String> showBoughtProducts(){
        ArrayList<String> show = new ArrayList<>();
        for (Good g:
                UserHandler.currentBuyer.getBoughtGoods()) {
            show.add(g.getName());
        }
        return show;
    }
    public static void rateProduct(String goodName, int score) {
        Good good = Good.getGoodByName(goodName,ManageInfo.allGoods);
        Score s = new Score(UserHandler.currentBuyer,score,good);
        good.addScore(s);
    }

    public static int viewBalance() {
        return UserHandler.currentBuyer.getBalance();
    }

    public static ArrayList<String> viewDiscountCodes() {
        ArrayList<String> show = new ArrayList<>();
        for (Discount d:
        UserHandler.currentBuyer.getMyDiscounts()) {
            show.add(d.toString());
        }
        return show;
    }
}
