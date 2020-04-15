package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Discount {
    private int code;
    private Date startDate;
    private Date endDate;
    private int percentReduction;
    private int maxReductionAmount;
    private int usageNumber;
    private ArrayList<User> users;
    private static ArrayList<Discount> discounts;

    public Discount(int code, Date startDate, Date endDate, int percentReduction, int maxReductionAmount, int usageNumber, ArrayList<User> users) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentReduction = percentReduction;
        this.maxReductionAmount = maxReductionAmount;
        this.usageNumber = usageNumber;
        this.users = users;
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
}
