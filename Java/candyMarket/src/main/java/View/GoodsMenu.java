package View;

import Controller.GoodsManaging;
import Model.Filter;
import Model.FilterAndSort;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsMenu extends Menu {

    public GoodsMenu(String name,Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,viewCategories());
        subMenus.put(2,filtering());
        subMenus.put(3,sorting());
        subMenus.put(4,showProducts());
        this.setSubMenus(subMenus);
    }
    private Menu filtering(){
        return new Menu("Filtering",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Available filters :");
                for (Filter f:
                        FilterAndSort.filters) {
                    String line = f.getId()+". "+f.getName().toString();
                    if(f.isEnable()) ConsoleDesign.printColorFull(ConsoleDesign.GREEN_BACKGROUND,line);
                    else System.out.println(line);
                }
                ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,ConsoleDesign.divider);
            }
            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0) {
                    GoodsManaging.updateFixedGoods();
                    this.parentMenu.run();
                }
                else {
                    Filter currentFilter  = FilterAndSort.getFilterById(menuChanger,FilterAndSort.filters);
                    if (currentFilter.isEnable())
                        currentFilter.setEnable(false);
                    else currentFilter.run();
                    this.run();
                }
            }
        };
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
    private Menu sorting() {
        return new Menu("Sorting",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Available sotrs :");
                if(FilterAndSort.sortDescendingMode == true){
                    ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,"1. "+FilterAndSort.sortsType.values()[0].toString());
                }
                else System.out.println("1. "+FilterAndSort.sortsType.values()[0].toString());
                for(int i=1;i<FilterAndSort.sortsType.values().length;i++){
                    String line = i+1 +". "+FilterAndSort.sortsType.values()[i].toString();
                    if (FilterAndSort.sortsType.values()[i] == FilterAndSort.sortsType){
                        ConsoleDesign.printColorFull(ConsoleDesign.GREEN_BACKGROUND,line);
                    }
                    else System.out.println(line);
                }
                ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,ConsoleDesign.divider);
            }
            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else if(menuChanger == 1) {
                    if(FilterAndSort.sortDescendingMode == true) FilterAndSort.sortDescendingMode=false;
                    else FilterAndSort.sortDescendingMode = true;
                }
                else
                    FilterAndSort.sortsType = FilterAndSort.sortsType.values() [menuChanger-1];
                this.run();
            }
        };
    }

    private Menu showProducts() {
        return new Menu("ShowProducts",this) {
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

    private Menu showProduct() {

        return null;
    }

}
