package View;

import Controller.MenuChangeManaging;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    protected Menu parentMenu;
    private HashMap<Integer, Menu> subMenus;
    protected LoginType user = LoginType.DEFAULT;

    public Menu(String name, Menu parentMenu) {
    }

    public void setParentMenu(Menu parentMenu) {
    }

    public void setSubMenus(HashMap<Integer, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getName() {
        return name;
    }

    public void show() {
        for (Integer menuNum : subMenus.keySet()
             ) {
            System.out.println(menuNum+". "+this.name);
        }
        if(this.parentMenu==null) System.out.println(subMenus.size()+1+". exit");
        else System.out.println(subMenus.size()+1+". back");
    }

    public void execute()
    {
        Menu nextMenu = null;
        int menuChanger = MenuChangeManaging.changeMenu();
        if (menuChanger == this.subMenus.size()+1)
        {
            if(this.parentMenu==null)
                System.exit(1);
            else
                nextMenu=this.parentMenu;
        }
        else{
            nextMenu = subMenus.get(menuChanger);
        }
        nextMenu.show();
        nextMenu.execute();
    }
}
