package Model;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog {
    private int id;
    private Date date;
    private int totalAmount;
    private int discountAmount;
    private ArrayList<Good> goods;
    private String SellerName;
    private CartSituation buySituation;
}
