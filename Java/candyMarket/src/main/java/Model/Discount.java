package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private static ArrayList<Discount> discounts =  ManageInfo.allDiscounts;
    private int code;
    private LocalDate startDate;
    private LocalDate endDate;
    private int percentReduction;
    private int maxReductionAmount;
    private int usageNumber;
    private ArrayList<User> users;

    @Override
    public String toString() {
        return "Discount{" +
                "code=" + code +
                ", percentReduction=" + percentReduction +
                ", maxReductionAmount=" + maxReductionAmount +
                ", usageNumber=" + usageNumber +
                '}';
    }

    public Discount(int code, LocalDate startDate, LocalDate endDate, int percentReduction, int maxReductionAmount, int usageNumber) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentReduction = percentReduction;
        this.maxReductionAmount = maxReductionAmount;
        this.usageNumber = usageNumber;
        discounts.add(this);
    }

    public static ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
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
        discounts.remove(code);
    }
}
