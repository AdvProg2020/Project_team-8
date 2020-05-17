package Model;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    private long date;
    private int totalAmount;
    private int discountAmount;
    private HashMap<Integer,Good> goods;
    private String buyerName;
    private CartSituation buySituation;

    public Cart( int totalAmount, int discountAmount, HashMap<Integer,Good> goods, String sellerName, CartSituation buySituation) {
        this.date = System.currentTimeMillis();
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.goods = goods;
        this.buyerName = buyerName;
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

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String sellerName) {
        buyerName = buyerName;
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
        Buyer.currentBuyer.setBalance(((Buyer) User.currentUser).getBalance()-User.currentUser.getCart().getTotalAmount()-User.currentUser.getCart().getDiscountAmount());
    }
    public Boolean canPay(){
        if(Buyer.currentBuyer.getBalance()<User.currentUser.getCart().totalAmount- User.currentUser.getCart().discountAmount)
        return false;
        else return true;
    }
    public void createLogs(){
        BuyLog buyLog = new BuyLog(totalAmount,discountAmount,goods,buyerName);
    }
}
