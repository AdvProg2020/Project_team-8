package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class SellLog{
    private String address;
    private String phoneNumber;
    @Id
    private int id;
    private long date;
    private int totalAmount;
    private int saleAmount;
    @ElementCollection
    @JoinTable(name = "SellLog_Good")
    @MapKeyColumn(name = "Good")
    @Column(name = "mumber")
    private Map<String,Integer> goods;

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
    @Enumerated(EnumType.STRING)
    private CartSituation buySituation;
    public static SellLog getSellLogById(int id){
        for (SellLog sellLog : ManageInfo.allSellLogs) {
            if(sellLog.id==id) return sellLog;
        }
        return null;
    }
    public SellLog(){}
    public SellLog(int totalAmount, int saleAmount, HashMap<Good, Integer> goods, String buyerName) {
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
        Controller.saveOrUpdateObject(this);
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
            goodsOrginal.put(Good.getGoodByName(key),value);
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
