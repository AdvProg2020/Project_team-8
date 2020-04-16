package View;

import Controller.GoodsManaging;
import Model.FilterAndSort;

import java.util.ArrayList;

public class GoodsMenu extends Menu {

    public GoodsMenu(Menu parentMenu) {
        super("", parentMenu);
    }

    private Menu viewCategories() {
        ArrayList<String> categories = GoodsManaging.viewCategories();
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
