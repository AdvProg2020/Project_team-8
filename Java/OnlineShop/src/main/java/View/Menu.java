package View;

import Controller.MenuChangeManaging;

import java.util.HashMap;

public abstract class Menu {
    private String name;
    protected Menu parentMenu;
    private HashMap<Integer, Menu> subMenus;
    protected LoginType user = LoginType.DEFAULT;

    public Menu(String name, Menu parentMenu) {
    }

    public void setParentMenu(Menu parentMenu) {
    }

    public String getName() {
        return name;
    }

    public void show() {

    }

    public void execute() {
        int menuChanger = MenuChangeManaging.changeMenu();
    }
}
