package Model;

import java.util.Date;
import java.util.List;

public class Discount {
    private int code;
    private Date startDate;
    private Date endDate;
    private int percentReduction;
    private int maxReductionAmount;
    private int usageNumber;
    private List<User> users;
}
