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
    private CardSituation sellSituation;
}
