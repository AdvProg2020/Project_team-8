package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private ArrayList<Good> goods;
    private HashMap<Good, String> amountOfPerGood;
    private CartSituation cardSituation;
    private int totalAmount;

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public void setAmountOfPerGood(HashMap<Good, String> amountOfPerGood) {
        this.amountOfPerGood = amountOfPerGood;
    }

    public void setCardSituation(CartSituation cardSituation) {
        this.cardSituation = cardSituation;
    }

    public Cart(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
