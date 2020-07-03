package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SellLog {
    private String address;
    private String phoneNumber;
    private int id;
    private long date;
    private int totalAmount;
    private int saleAmount;
    private HashMap<Good,Integer> goods;

    {
        new HashMap<>() {
            @Override
            public String toString() {
                String string = new String();
                for (Good good : goods.keySet()) {
                    string = good.getName() + ", ";
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
        this.goods = goods;
        this.buyerName = buyerName;
        this.buySituation = CartSituation.CONFIRMATION;
        this.id = ManageInfo.allBuyLogs.size();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public HashMap<Good, Integer> getGoods() {
        return goods;
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
