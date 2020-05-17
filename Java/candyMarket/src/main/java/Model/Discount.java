package Model;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private int code;
    private Date startDate;
    private Date endDate;
    private int percentReduction;
    private int maxReductionAmount;
    private int usageNumber;
    private ArrayList<User> users;
    public Discount(int code, Date startDate, Date endDate, int percentReduction, int maxReductionAmount, int usageNumber) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentReduction = percentReduction;
        this.maxReductionAmount = maxReductionAmount;
        this.usageNumber = usageNumber;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}
