package Server.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    @JoinTable(name = "BuyLog_Good_map")
    @MapKeyClass(Good.class)
    @MapKeyColumn(name = "Good_Name")
    @Column(name = "Number")
    private Map<Good,Integer> goods;
    private String buyerName;
    @Enumerated(EnumType.STRING)
    private CartSituation buySituation;
    public BuyLog(){}
    public BuyLog(int totalAmount, int discountAmount, HashMap<Good,Integer> goods, String buyerName) {
        this.date = System.currentTimeMillis();
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.goods = goods;
        this.buyerName = buyerName;
        this.buySituation = CartSituation.CONFIRMATION;
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
        for (Good good : goods.keySet()) {
            purchasedGoods.add(good);
        }
        return purchasedGoods;
    }
}
