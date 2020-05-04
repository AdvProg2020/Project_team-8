package View;

import Controller.SalesManaging;

import java.util.ArrayList;

public class SalesMenu extends Menu {

    public SalesMenu(String name,Menu parentMenu) {
        super(name, parentMenu);
    }

    private Menu viewCategories() {
        ArrayList<String> categories = SalesManaging.viewCategories();
        return null;
    }

    private Menu filtering() {
        return null;
    }

    private Menu sorting() {
        return null;
    }

    private Menu showProducts() {
        return null;
    }

    private Menu showProduct() {
        return null;
    }
}
