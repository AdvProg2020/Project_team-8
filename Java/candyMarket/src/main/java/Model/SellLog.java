package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SellLog {
    private String address;
    private String phoneNumber;
    private int id;
    private long date;
    private int totalAmount;
    private int saleAmount;
    private HashMap<String,Integer> goods;

    {
        new HashMap<>() {
            @Override
            public String toString() {
                String string = new String();
                for (String good : goods.keySet()) {
                    string = good + ", ";
                }
                return string;
            }
        };
    }

    private String buyerName;
    private CartSituation buySituation;

    public SellLog(int totalAmount,int saleAmount, HashMap<Good, Integer> goods, String buyerName) {
        this.date = System.currentTimeMillis();
        this.totalAmount = totalAmount;
        this.saleAmount = saleAmount;
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
    }

    public String getBuyerName() {
        return buyerName;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public HashMap<Good, Integer> getGoods() {
        HashMap<Good,Integer> goodsOrginal = new HashMap<>();
        for(Map.Entry<String, Integer> entry : goods.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            goodsOrginal.put(Good.getGoodByName(key,ManageInfo.allGoods),value);
            // do what you have to do here
            // In your case, another loop.
        }
        return goodsOrginal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
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
