package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog {
    private String address;
    private String phoneNumber;
    private int id;
    private long date;
    private int totalAmount;
    private int discountAmount;
    private HashMap<Integer,Good> goods;
    private String buyerName;
    private CartSituation buySituation;

    public BuyLog(int totalAmount, int discountAmount, HashMap<Integer,Good> goods, String sellerName) {
        this.date = System.currentTimeMillis();
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.goods = goods;
        this.buyerName = buyerName;
        this.buySituation = CartSituation.CONFIRMATION;
    }


    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }


    public CartSituation getBuySituation() {
        return buySituation;
    }

    public void setBuySituation(CartSituation buySituation) {
        this.buySituation = buySituation;
    }

    public int getId() {
        return id;
    }
}
