package Client.Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static String address;
    private static String buyerName;
    private static CartSituation buySituation;
    private static String phoneNumber;
    private static int totalAmount = 0;
    private static int discountAmount = 0;
    private static boolean Payed = false;
    private static HashMap<Good,Integer> goods = new HashMap<>();
    private static String fxml;


    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address1) { address = address1;
    }

    public static boolean isPayed() {
        return Payed;
    }

    public static void setPayed(boolean Payed) {
        Cart.Payed = Payed;
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
        UserHandler.currentBuyer.setBalance(UserHandler.currentBuyer.getBalance()- UserHandler.currentCart.getTotalAmount());
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
        if(Buyer.currentBuyer.getBalance()< UserHandler.currentCart.getTotalAmount()) return false;
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
}
