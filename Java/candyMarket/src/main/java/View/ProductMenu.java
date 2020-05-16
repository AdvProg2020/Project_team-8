package View;

import Controller.GoodManaging;
import Controller.GoodsManaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ProductMenu extends Menu {
    String goodName;
    String goodNameCompareWith;
    public ProductMenu(Menu parentMenu,String goodName) {
        super("ProductMenu", parentMenu);
        this.goodName = goodName;
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,digest());
        subMenus.put(2,attributes());
        subMenus.put(3,compare());
        subMenus.put(4,comments());
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
            public void run() {
                compareWithProduct().run();
            }

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
                    goodNameCompareWith = products.get(menuChanger);
                    compare().run();
                }
            }
        };
    }

    private Menu comments()
    {
        return new Menu("Comments",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                ConsoleDesign.printColorFull(ConsoleDesign.BLUE,GoodManaging.showComments());
                System.out.println(ConsoleDesign.divider);
                System.out.println("0. back");
                System.out.println("1. add comment");
            }
            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else if(menuChanger == 1){
                    if(!GoodManaging.CanComment())
                        throw ViewException.cantComment();
                    String title;
                    String content;
                    ConsoleDesign.printColorFull(ConsoleDesign.GREEN,"please enter Title : ");
                    title = ConsoleCmd.scanner.nextLine();
                    ConsoleDesign.printColorFull(ConsoleDesign.GREEN,"please enter Content : ");
                    content = ConsoleCmd.scanner.nextLine();
                    GoodManaging.addComment(title,content);
                    ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,"you commented successfully");
                    this.parentMenu.run();
                }
            }
        };
    }
}
