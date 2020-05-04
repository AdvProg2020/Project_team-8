package View;

import Controller.BuyerManaging;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu parentMenu) {
        super("", parentMenu);
    }

    private Menu receiverInformation() {
        return null;
    }

    private Menu discountCode() {
        BuyerManaging.discountCodeEntry();
        return null;
    }

    private Menu payment() {
        return null;
    }
}
