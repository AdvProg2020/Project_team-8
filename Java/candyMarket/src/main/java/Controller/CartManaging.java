package Controller;

import Model.*;

//import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class CartManaging {
    public static void viewCart() {

    }
    public static void receiveInformation(String address,String phoneNumber){
        User.currentUser.getCart().setAddress(address);
        User.currentUser.getCart().setPhoneNumber(phoneNumber);
        User.currentUser.getCart().setBuySituation(CartSituation.SHIPPING);
    }
    public static ArrayList<String> showProductsFromCart() {
        return null;
    }

    public static Good viewProductIdFromCart() {
        return null;
    }

    public static int increaseProductNumberInCart(String good) {
        return 0;
    }
    public static int decreaseProductNumberInCart(String good) {

        return 0;
    }

    public static int showTotalPrice() {

        return 0;
    }
    public static boolean addDiscountCode(String code)
    {
        //Error
        Discount discount = Discount.getDiscountByCode(code);
        if(discount==null)
        return false;
        else
            if(!discount.getUsers().contains(User.currentUser))
                return false;
        //Success
        User.currentUser.getCart().addDiscount(discount);
        return true;
    }
    public static boolean pay()
    {
        if(!User.currentUser.getCart().canPay())
            return false;
        else User.currentUser.getCart().pay();
        return true;

    }
    public static void purchase() {
        User.currentUser.getCart().setBuySituation(CartSituation.CONFIRMATION);
        User.currentUser.getCart().createLogs();
    }
}
