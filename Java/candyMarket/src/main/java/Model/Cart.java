package Model;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.ArrayList;
import java.util.Date;

public class Cart {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;
    private int id;
    private Date date;
    private int totalAmount;
    private int discountAmount;
    private ArrayList<Good> goods;
    private String SellerName;
    private CartSituation buySituation;

    public Cart(Date date, int totalAmount, int discountAmount, ArrayList<Good> goods, String sellerName, CartSituation buySituation) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.goods = goods;
        SellerName = sellerName;
        this.buySituation = buySituation;
    }
    public void addDiscount(Discount discount){
        discountAmount = totalAmount* discount.getPercentReduction()/100;
        if(discountAmount > discount.getMaxReductionAmount()) discountAmount=discount.getMaxReductionAmount();
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getId() {
        return id;
    }
    public void pay(){
        ((Buyer) User.currentUser).setBalance(((Buyer) User.currentUser).getBalance()-User.currentUser.getCart().getTotalAmount()-User.currentUser.getCart().getDiscountAmount());
    }
    public void canPay(){

        return false;
    }
}
