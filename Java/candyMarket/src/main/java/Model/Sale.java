package Model;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Sale {

    private ArrayList<Good> goods;
    private LocalDate startTime;
    private LocalDate endTime;
    private int salePercentageAmount;
    public static ArrayList<String> productsOnSaleName;

    public Sale(LocalDate startTime, LocalDate endTime, int amount) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.salePercentageAmount = amount;
        UserHandler.currentSeller.addSale(this);
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

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
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
        this.goods = goods;
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
}
