package Server.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
@Entity
public class Sale {
    private ItemCreationSituation situation;
    @Id
    private int id;
    private ArrayList<Good> goods;
    private Date startTime;
    private Date endTime;
    private int salePercentageAmount;
    public Sale(){}
    public Sale(ItemCreationSituation situation, Date startTime, Date endTime, int amount) {
        this.situation = situation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.salePercentageAmount = amount;
        this.id = ManageInfo.allSales.size();
        ManageInfo.allSales.add(this);
    }

    public ItemCreationSituation getSituation() {
        return situation;
    }

    public void setSituation(ItemCreationSituation situation) {
        this.situation = situation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getAmount() {
        return salePercentageAmount;
    }

    public void setAmount(int amount) {
        this.salePercentageAmount = amount;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public static Boolean isSaleWithId(int id){
        return null;
    }

    public static Sale getSaleById(int id){
        return null;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "situation=" + situation +
                ", id=" + id +
                ", goods=" + goods +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", salePercentageAmount=" + salePercentageAmount +
                '}';
    }
}
