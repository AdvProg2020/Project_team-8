package Model;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    private List<BuyLog> myBuyLog;
    private Card card;
    private ArrayList<Discount> myDiscounts;
    private ArrayList<BuyLog> myLogs;
}
