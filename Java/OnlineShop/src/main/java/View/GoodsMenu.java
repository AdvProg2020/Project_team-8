package View;

import Controller.GoodsManaging;
import Model.FilterAndSort;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsMenu extends Menu {

    public GoodsMenu(Menu parentMenu) {
        super("", parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,viewCategories());

    }

    private Menu viewCategories() {
        return new Menu("viewCategories",this) {
            @Override
            public void show() {
                ArrayList<String> categories = GoodsManaging.viewCategories();
                HashMap<Integer , Menu> subMenus = new HashMap <Integer, Menu>();
                for (String category:
                        categories) {
                    System.out.println();
                }
            }

            @Override
            public void execute() {
                this.execute();
            }
        };
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
