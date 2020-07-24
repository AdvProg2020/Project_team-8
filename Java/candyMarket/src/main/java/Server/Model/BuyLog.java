package Server.Model;

import Client.DataHandler.DataAccessor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class BuyLog {
    private String address;
    private String phoneNumber;
    @Id
    private int id;
    private long date;
    private int totalAmount;
    private int discountAmount;
    @ElementCollection
    @JoinTable(name = "BuyLog_Good")
    @MapKeyColumn(name = "Good")
    @Column(name = "mumber")
    private Map<String,Integer> goods;
    private String buyerName;
    @Enumerated(EnumType.STRING)
    private CartSituation buySituation;
    public BuyLog(){}
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
    public static BuyLog getBuyLogById(int id){
        for (BuyLog buyLog : ManageInfo.allBuyLogs) {
            if(buyLog.getId()==id) return buyLog;
        }
        return null;
    }
    public List<Good> getGoods() {
        List<Good> purchasedGoods = new ArrayList<>();
        for (String good : goods.keySet()) {
            purchasedGoods.add(Good.getGoodByName(good));
        }
        return purchasedGoods;
    }
}
