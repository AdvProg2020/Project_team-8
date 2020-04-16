package Model;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    private ArrayList<BuyLog> myBuyLog;
    private Cart cart;
    private ArrayList<Discount> myDiscounts;
    private ArrayList<BuyLog> myLogs;
    public Buyer(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, UserType type) {
        super(userName, firstName, lastName, email, phoneNumber, passWord, type);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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


}
