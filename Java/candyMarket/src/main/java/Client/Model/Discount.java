package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Discount {
    @Id
    private String code;
    private LocalDate startDate;
    private LocalDate endDate;
    private int percentReduction;
    private int maxReductionAmount;
    private int usageNumber;
    @ElementCollection
    private List<String> buyers;
    @Override
    public String toString() {
        return "Discount{" +
                "code=" + code +
                ", percentReduction=" + percentReduction +
                ", maxReductionAmount=" + maxReductionAmount +
                ", usageNumber=" + usageNumber +
                '}';
    }
    public Discount(){}
    public Discount(String code, LocalDate startDate, LocalDate endDate, int percentReduction, int maxReductionAmount, int usageNumber) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentReduction = percentReduction;
        this.maxReductionAmount = maxReductionAmount;
        this.usageNumber = usageNumber;
        this.buyers = new ArrayList<>();
        Controller.saveOrUpdateObject(this);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPercentReduction() {
        return percentReduction;
    }

    public void setPercentReduction(int percentReduction) {
        this.percentReduction = percentReduction;
    }

    public int getMaxReductionAmount() {
        return maxReductionAmount;
    }

    public void setMaxReductionAmount(int maxReductionAmount) {
        this.maxReductionAmount = maxReductionAmount;
    }

    public int getUsageNumber() {
        return usageNumber;
    }

    public void setUsageNumber(int usageNumber) {
        this.usageNumber = usageNumber;
    }

    public List<Buyer> getUsers() {
        List<Buyer> buyersOrginal = new ArrayList<>();
        for (String s:
             buyers) {
             buyersOrginal.add((Buyer) Buyer.getUserByUsername(s));
        }
        return buyersOrginal;
    }

    public void setBuyers(List<Buyer> buyers) {
        List<String> buyersString = new ArrayList<>();
        for (Buyer b:
                buyers) {
            buyersString.add(b.getUsername());
        }
        this.buyers = buyersString;
    }
    public static Discount getDiscountById(String id) {
        return null;
    }
    public static Discount getDiscountByCode(String code) {
        return null;
    }

    public static Boolean isDiscountWithId(String id) {
        return null;
    }

    public static void removeCode(Discount code) {
        ManageInfo.allDiscounts.remove(code);
    }
}
