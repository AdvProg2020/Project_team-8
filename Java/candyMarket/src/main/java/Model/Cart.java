package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static String address;
    private static String buyerName;
    private static CartSituation buySituation;
    private static String phoneNumber;
    private static int totalAmount = 0;
    private static int discountAmount = 0;
    private static HashMap<Good,Integer> goods = new HashMap<>();
    private static String fxml;


    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address1) { address = address1;
    }

    public static void setFxml(String fxml) {
        Cart.fxml = fxml;
    }

    public static String getFxml() {
        return fxml;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber1) {
        phoneNumber = phoneNumber1;
    }

    public static HashMap<Good, Integer> getGoods() {
        return goods;
    }

    public static void addGood(Good good) {
        goods.put(good, 1);
    }

    public static void setGoods(HashMap<Good, Integer> goods1) {
        goods = goods1;
    }

    public static void addDiscount(Discount discount){
        discountAmount = totalAmount* discount.getPercentReduction()/100;
        if(discountAmount > discount.getMaxReductionAmount()) discountAmount=discount.getMaxReductionAmount();
        totalAmount -= discountAmount;
    }

    public static void pay(){
        for (Good g:
             goods.keySet()) {
            g.addBuyers(UserHandler.currentBuyer);
            UserHandler.currentBuyer.getBoughtGoods().add(g);
        }
        UserHandler.currentBuyer.setBalance(UserHandler.currentBuyer.getBalance()-UserHandler.currentCart.getTotalAmount());
        resetCart();
    }

    public static void resetCart(){
        address = null;
        buyerName = null;
        phoneNumber = null;
        totalAmount = 0;
        discountAmount = 0;
        goods = new HashMap<>();
    }

    public static int getTotalAmount() {
        return totalAmount;
    }

    public static void setTotalAmount() {
        totalAmount = 0;
        for (Good good : goods.keySet()) {
            totalAmount += good.getPrice()*goods.get(good) ;
        }
    }

    public static void setTotalAmountWithDiscount() {
        totalAmount = 0;
        for (Good good : goods.keySet()) {
            totalAmount += good.getPrice()*goods.get(good) ;
        }
        totalAmount = (int) (totalAmount *  ((double)(100 - (double)discountAmount) / 100));
    }

    public static int getDiscountAmount() {
        return discountAmount;
    }

    public static void setDiscountAmount(int discountAmount1) {
        discountAmount = discountAmount1;
    }

    public static String getBuyerName() {
        return buyerName;
    }

    public static void setBuyerName(String sellerName) {
        buyerName = buyerName;
    }

    public static CartSituation getBuySituation() {
        return buySituation;
    }

    public static void setBuySituation(CartSituation buySituation1) {
        buySituation = buySituation1;
    }

    public static Boolean canPay(){
        if(Buyer.currentBuyer==null)
        return false;
        if(Buyer.currentBuyer.getBalance()<UserHandler.currentCart.getTotalAmount()) return false;
        else return true;
    }

    public static boolean increaseProduct(Good good) {
        if (good.getStock() == 0)
            return false;
        if (goods.get(good) == null)
            goods.put(good, 1);
        else
            goods.put(good, goods.get(good) + 1);
        good.setStock(good.getStock() - 1);
        return true;
    }

    public static void decreaseProduct(Good good){
        if(goods.get(good)<=1)
            goods.remove(good);
        else
            goods.put(good,goods.get(good)-1);
        good.setStock(good.getStock()+1);
    }

    public static void createLogs(){
        HashMap<Seller,HashMap<Good,Integer>> logs = new HashMap<>();
        for(Map.Entry<Good,Integer> entry : goods.entrySet()) {
            Good g = entry.getKey();
            int gNum = entry.getValue();
            if(logs.keySet().contains(g.getSeller())) {
                HashMap<Good, Integer> currentGoods = logs.get(g.getSeller());
                if(currentGoods.containsValue(g))
                    currentGoods.put(g,currentGoods.get(g)+gNum);
                else currentGoods.put(g,gNum);
                logs.put(g.getSeller(),currentGoods);
            }
            else
            {
                HashMap<Good,Integer> goods = new HashMap<Good,Integer>(){
                    {
                        put(g,gNum);
                    }
                };
                logs.put(g.getSeller(),goods);
            }
        }
        for(Map.Entry<Seller, HashMap<Good, Integer>> entry : logs.entrySet()) {
            Seller key = entry.getKey();
            HashMap<Good,Integer> value = entry.getValue();
            int totalAmount = 0;
            int saleAmount = 0;
            for(Map.Entry<Good,Integer> entry2 : value.entrySet()) {
               Good key2 = entry2.getKey();
               int value2 = entry2.getValue();
               totalAmount+=key2.getPriceAfterSale();
               saleAmount+=key2.getSalePercentageAmount()*key2.getPrice()/100;
            }
            BuyLog buyLog = new BuyLog(totalAmount-discountAmount,discountAmount,value,key.getUsername());
            SellLog sellLog = new SellLog(totalAmount,saleAmount,value,Buyer.currentBuyer.getUsername());
            Buyer.currentBuyer.addMyLogs(buyLog);
            Seller.currentSeller.addMySellLog(sellLog);
            ManageInfo.allBuyLogs.add(buyLog);
            ManageInfo.allSellLogs.add(sellLog);
        }
        }
}
