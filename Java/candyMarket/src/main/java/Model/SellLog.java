package Model;

import java.util.ArrayList;
import java.util.Date;

public class SellLog {
    private int id;
    private Date date;
    private int amount;
    private int discountAmount;
    private ArrayList<Good> goods;
    private String buyerName;
    private CartSituation sellSituation;

    public SellLog(Date date, int amount, int discountAmount, ArrayList<Good> goods, String buyerName, CartSituation sellSituation) {
        this.date = date;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.goods = goods;
        this.buyerName = buyerName;
        this.sellSituation = sellSituation;
    }

    public int getId() {
        return id;
    }
}
