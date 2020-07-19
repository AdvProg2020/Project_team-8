package Server.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Sale {
    @ElementCollection
    private List<String> goods;
    private LocalDate startTime;
    private LocalDate endTime;
    private int salePercentageAmount;
    public static List<String> productsOnSaleName;
    @Id
    private int id;
    public Sale(){}
    public Sale(LocalDate startTime, LocalDate endTime, int amount) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.salePercentageAmount = amount;
        this.id = ManageInfo.allSales.size();
        ManageInfo.allSales.add(this);
        UserHandler.currentSeller.addSale(this);
    }
    public static Sale getSaleById(int id){
        for (Sale sale : ManageInfo.allSales) {
            if(sale.id==id)
                return sale;
        }
        return null;
    }
    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public int getSalePercentageAmount() {
        return salePercentageAmount;
    }

    public void setSalePercentageAmount(int salePercentageAmount) {
        this.salePercentageAmount = salePercentageAmount;
    }

    public List<Good> getGoods() {
        List<Good> goods = new ArrayList<>();
        for (String good : this.goods) {
            goods.add(Good.getGoodByName(good,ManageInfo.allGoods));
        }
        return goods;
    }

    public void setGoods(List<Good> goods) {
        productsOnSaleName = new ArrayList<>() {
            @Override
            public String toString() {
                String string = "";
                for (String s : productsOnSaleName) {
                    string = "| " + s;
                }
                return string;
            }
        };
        List<String> goodsStr = new ArrayList<>();
        for (Good good : goods) {
            goodsStr.add(good.getName());
        }
        this.goods = goodsStr;
        for (Good good : ManageInfo.allGoods) {
            for (Good good1 : goods) {
                if (good == good1) {
                    productsOnSaleName.add(good.getName());
                    good.setSalePercentageAmount(this.salePercentageAmount);
                    good.setPrice(good.getPriceAfterSale());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Sale{" +
                ", goods=" + goods +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", salePercentageAmount=" + salePercentageAmount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
