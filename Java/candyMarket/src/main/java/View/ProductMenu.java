package View;

import Controller.GoodManaging;
import Controller.GoodsManaging;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductMenu extends Menu {
    String goodName;
    String goodNameCompareWith;
    public ProductMenu(String name, Menu parentMenu,String goodName) {
        super("ProductMenu", parentMenu);
        this.goodName = goodName;
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,digest());
        subMenus.put(2,attributes());
        this.setSubMenus(subMenus);
    }

    private Menu digest() {
        return new Menu("Digest",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("digest info : ");
                ConsoleDesign.printColorFull(GoodManaging.digest(goodName),ConsoleDesign.GREEN);
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger==0)
                    this.parentMenu.run();
                else throw ViewException.invalidNumber();
            }
        };
    }

    private Menu attributes() {
        return new Menu("Digest",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("all info : ");
                ConsoleDesign.printColorFull(GoodManaging.attributes(goodName),ConsoleDesign.GREEN);
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger==0)
                    this.parentMenu.run();
                else throw ViewException.invalidNumber();
            }
        };
    }
    private Menu compare(){
        return new Menu("Compare",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("compare " + goodName + " with " + goodNameCompareWith + " : ");
                ConsoleDesign.printColorFull(GoodManaging.compareToAnotherProduct(goodName, goodNameCompareWith), ConsoleDesign.GREEN);
            }

            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if (menuChanger == 0)
                    this.parentMenu.run();
                else throw ViewException.invalidNumber();
            }
        };
    }
    private Menu compareWithProduct()
    {
        return new Menu("CompareWithProduct",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Available products : ");
                ArrayList<String> products  = GoodsManaging.showProducts();
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

                }
            }
        };
    }

    private Menu comments()
    {
        return null;
    }
}
