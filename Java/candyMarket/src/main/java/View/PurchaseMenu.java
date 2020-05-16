package View;

import Controller.BuyerManaging;

import java.util.HashMap;

public class PurchaseMenu extends Menu {

    public PurchaseMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
        subMenus = new HashMap<>();
        
    }
}
