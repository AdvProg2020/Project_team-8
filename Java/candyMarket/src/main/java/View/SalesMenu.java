package View;

import Controller.GoodsManaging;
import Controller.SalesManaging;
import View.FilterMenus.Filtering;
import View.FilterMenus.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

public class SalesMenu extends Menu {

    public SalesMenu(String name,Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,showOffs());
        subMenus.put(2,new Filtering("Filtering",this));
        subMenus.put(3,new Sorting("Sorting",this));
        this.setSubMenus(subMenus);
    }

    private Menu showOffs() {
        return new Menu("Show Offs",this) {
            ArrayList<String> products  = SalesManaging.showOffs();
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
}
