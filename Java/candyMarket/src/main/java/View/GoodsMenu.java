package View;

import Controller.GoodsManaging;
import Model.Filter;
import Model.FilterAndSort;
import View.FilterMenus.Filtering;
import View.FilterMenus.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsMenu extends Menu {

    public GoodsMenu(String name,Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,viewCategories());
        subMenus.put(2,new Filtering("Filtering",this));
        subMenus.put(3,new Sorting("Sorting",this));
        subMenus.put(4,showProducts());
        this.setSubMenus(subMenus);
    }

    private Menu viewCategories() {
        return new Menu("ViewCategories",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                ArrayList<String> categories = GoodsManaging.viewCategories();
                System.out.println("0. back");
                for (String category:
                        categories) {
                    System.out.println("-");
                }
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else throw ViewException.invalidNumber();
            }
        };
    }


    private Menu showProducts() {
        return new Menu("ShowProducts",this) {
            ArrayList<String> products  = GoodsManaging.showProducts();
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Available products : ");
                for (String s:
                     products) {
                    System.out.println(products.indexOf(s)+1+". "+s);
                }
            }
            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else{
                    new ProductMenu(this,products.get(menuChanger));
                }
            }
        };
    }

    private Menu showProduct() {

        return null;
    }

}
