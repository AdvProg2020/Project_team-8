package Controller;

import Model.*;

//import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class CartManaging {
    public static void viewCart() {

    }
    public static void receiveInformation(String address,String phoneNumber){
        UserHandler.currentCart.setAddress(address);
        UserHandler.currentCart.setPhoneNumber(phoneNumber);
        UserHandler.currentCart.setBuySituation(CartSituation.SHIPPING);
    }
    public static ArrayList<String> showProductsFromCart() {
        ArrayList<String> show = new ArrayList<>();
        for (Map.Entry<Good,Integer> entry : UserHandler.currentCart.getGoods().entrySet()){
            show.add(entry.getKey().getName()+"  :  "+entry.getValue().toString());
        }
        return show;
    }

    public static Good viewProductIdFromCart() {
        return null;
    }
    public static boolean increaseProductNumberInCart(String goodName)
    {
        Good good = Good.getGoodByName(goodName,ManageInfo.allGoods);
        return UserHandler.currentCart.increaseProduct(good);
    }
    public static void decreaseProductNumberInCart(String goodName) {
        Good good = Good.getGoodByName(goodName,ManageInfo.allGoods);
        UserHandler.currentCart.decreaseProduct(good);
    }

    public static int showTotalPrice() {
        return UserHandler.currentCart.getTotalAmount();
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
        UserHandler.currentCart.addDiscount(discount);
        return true;
    }
    public static boolean pay()
    {
        if(!UserHandler.currentCart.canPay())
            return false;
        UserHandler.currentCart.createLogs();
        return true;
    }
    public static void purchase() {
        UserHandler.currentCart.setBuySituation(CartSituation.CONFIRMATION);
    }
}
