package View;

import Controller.GoodManaging;

public class ProductMenu extends Menu {
    public ProductMenu(String name, Menu parentMenu) {
        super("", parentMenu);
    }

    private Menu digest() {
        GoodManaging.digest();
        return null;
    }

    private Menu attributes() {
        return null;
    }

    private Menu compare() {
        return null;
    }

    private Menu comments() {
        return null;
    }
}
