package Model;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private ItemCreationSituation situation;
    private int id;
    private ArrayList<Good> goods;
    private Date startTime;
    private Date endTime;
    private int amount;

    public Sale(ItemCreationSituation situation, int id, Date startTime, Date endTime, int amount) {
        this.situation = situation;
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }
}
