package View;

import java.util.HashMap;

public class MainMenu extends Menu{

    public MainMenu() {
        super("MainMenu", null);
        HashMap<Integer,Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1 , new ClientsMenu("ClientsMenu",this));
        subMenus.put(2 , new ClientsMenu("GoodsMenu",this));
        subMenus.put(3 , new ClientsMenu("SalesMenu",this));
        this.setSubMenus(subMenus);
    }
}
