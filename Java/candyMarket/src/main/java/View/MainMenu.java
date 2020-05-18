package View;

import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu() {
        super("MainMenu", null);
        HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, new LoginOrRegister(this));
        subMenus.put(2, new PurchaseMenu("Cart Menu",this));
        subMenus.put(3, new GoodsMenu("Goods Menu", this));
        subMenus.put(4, new SalesMenu("Sales Menu", this));
        subMenus.put(5, new ClientsMenu("Clients Menu", this));
        this.setSubMenus(subMenus);
    }

    @Override
    public void show() {
        System.out.println(this.name);
        if(this.parentMenu==null) System.out.println("0. exit");
        else System.out.println("0. back");
        if(subMenus == null) return;
        for (Integer menuNum : subMenus.keySet()) {
            if (menuNum !=5)
                System.out.println(menuNum+". "+subMenus.get(menuNum).getName());
        }
    }
}
