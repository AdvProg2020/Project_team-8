package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BuyLog {
    private String address;
    private String phoneNumber;
    private int id;
    private long date;
    private int totalAmount;
    private int discountAmount;
    private HashMap<String,Integer> goods;
    private String buyerName;
    private CartSituation buySituation;

    public BuyLog(int totalAmount, int discountAmount, HashMap<Good,Integer> goods, String buyerName) {
        this.date = System.currentTimeMillis();
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        HashMap<String,Integer> goodsString = new HashMap<>();
        for(Map.Entry<Good, Integer> entry : goods.entrySet()) {
            Good key = entry.getKey();
            int value = entry.getValue();
            goodsString.put(key.getName(),value);
            // do what you have to do here
            // In your case, another loop.
        }
        this.goods = goodsString;
        this.buyerName = buyerName;
        this.buySituation = CartSituation.ON_THE_WAY;
        this.id = ManageInfo.allBuyLogs.size();
        ManageInfo.allBuyLogs.add(this);
    }

    public String getBuyerName() {
        return buyerName;
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

    public String getAddress() {
        return address;
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

    public ArrayList<Good> getGoods() {
        ArrayList<Good> purchasedGoods = new ArrayList<>();
        for (String good : goods.keySet()) {
            purchasedGoods.add(Good.getGoodByName(good,ManageInfo.allGoods));
        }
        return purchasedGoods;
    }
}
