package View;

import Model.DefaultUser;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parentMenu;
    private HashMap<Integer, Menu> subMenus;
    protected static Scanner scanner;

    public Menu(String name, Menu parentMenu) {
    }

    public static void setScanner(Scanner scanner) {
    }

    public void setParentMenu(Menu parentMenu) {
    }

    public String getName() {
        return name;
    }

    public void show() {

    }

    public void execute() {

    }
}
