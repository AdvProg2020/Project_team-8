package Server.Model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;
@Entity
public class SellLog {
    private String address;
    private String phoneNumber;
    @Id
    private int id;
    private long date;
    private int totalAmount;
    private int saleAmount;
    @ElementCollection
    @JoinTable(name = "SellLog_Good_map")
    @MapKeyClass(Good.class)
    @MapKeyColumn(name = "Good_Name")
    @Column(name = "Number")
    private Map<Good, Integer> goods;

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
    @Enumerated(EnumType.STRING)
    private CartSituation buySituation;
    public SellLog(){}
    public SellLog(int totalAmount, int saleAmount, HashMap<Good, Integer> goods, String buyerName) {
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
        return (HashMap<Good, Integer>) goods;
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
