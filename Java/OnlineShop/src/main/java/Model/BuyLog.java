package Model;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog {
    private int id;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BuyLog(Date date, int totalAmount, int discountAmount, ArrayList<Good> goods, String sellerName, CartSituation buySituation) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.goods = goods;
        SellerName = sellerName;
        this.buySituation = buySituation;
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

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    public CartSituation getBuySituation() {
        return buySituation;
    }

    public void setBuySituation(CartSituation buySituation) {
        this.buySituation = buySituation;
    }

    private Date date;
    private int totalAmount;
    private int discountAmount;
    private ArrayList<Good> goods;
    private String SellerName;
    private CartSituation buySituation;

    public int getId() {
        return id;
    }
}
