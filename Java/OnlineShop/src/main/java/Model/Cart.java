package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private ArrayList<Good> goods;
    private HashMap<Good, String> amountOfPerGood;
    private int totalPrice;
    private CartSituation cardSituation;
    private int totalAmount;
}
