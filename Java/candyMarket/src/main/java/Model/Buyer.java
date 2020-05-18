package Model;

import java.util.ArrayList;

public class Buyer extends User {
    private ArrayList<BuyLog> myBuyLog;
    private ArrayList<Discount> myDiscounts;
    private ArrayList<BuyLog> myLogs;
    private int balance;
    public static Buyer currentBuyer;
    public Buyer(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.balance=0;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static void CreateNewBuyer() {

    }
    public ArrayList<String> viewCart(Cart cart) {
        return null;
    }

    public ArrayList<String> viewGoodsInCart(Cart cart) {
        return null;
    }

    public void increaseGoodAmountInCart(Cart cart, Good good) {

    }

    public void decreaseGoodAmountInCart(Cart cart, Good good) {

    }

    public void purchase(CartSituation cartSituation) {

    }

    public void receiveInformation() {

    }

    public void applyDiscountCode(Cart cart, Discount discount) {

    }

    public void payment(Cart cart) {

    }

    public ArrayList<String> showOrders() {
        return null;
    }

    public void rate(Good good, int score) {

    }

    public String viewBuyerBalance() {
        return null;
    }

    public ArrayList<String> viewAllDiscountsCode() {
        return null;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<BuyLog> getMyLogs() {
        return myLogs;
    }

    public void setMyLogs(ArrayList<BuyLog> myLogs) {
        this.myLogs = myLogs;
    }
    public void addMyLogs(BuyLog buyLog) {
        this.myBuyLog.add(buyLog);
    }
}
