package View;

import Controller.GoodsManaging;
import View.FilterAndSort.Filter;
import View.FilterAndSort.FilterAndSort;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsMenu extends Menu {

    ArrayList<Filter> filters = FilterAndSort.createAllFilters();
    FilterAndSort.sortsType sortsType = FilterAndSort.sortsType.ALPHABETICALLY;
    Boolean sortDescendingMode = false;
    public GoodsMenu(String name,Menu parentMenu) {
        super(name, parentMenu);
        HashMap<Integer , Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1,viewCategories());
        subMenus.put(2,filtering());
        subMenus.put(3,sorting());
        this.setSubMenus(subMenus);
    }
    private Menu filtering(){
        return new Menu("filtering",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Available filters :");
                for (Filter f:
                        filters) {
                    String line = f.getId()+". "+f.getName().toString();
                    if(f.isEnable()) ConsoleDesign.printColorFull(ConsoleDesign.GREEN_BACKGROUND,line);
                    else System.out.println(line);
                }
                ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,ConsoleDesign.divider);
            }
            @Override
            public void execute() throws ViewException {
                int menuChanger = ConsoleCmd.scanner.nextInt();
                if(menuChanger == 0)
                    this.parentMenu.run();
                else {
                    Filter currentFilter  = FilterAndSort.getFilterById(menuChanger,filters);
                    if (currentFilter.isEnable())
                        currentFilter.setEnable(false);
                    else currentFilter.run();
                    this.run();
                }
            }
        };
    }
    private Menu viewCategories() {
        return new Menu("viewCategories",this) {
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
        return new Menu("sorting",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
                System.out.println("0. back");
                System.out.println("Available sotrs :");
                if(sortDescendingMode == true){
                    ConsoleDesign.printColorFull(ConsoleDesign.YELLOW,"1. "+FilterAndSort.sortsType.values()[0].toString());
                }
                else System.out.println("1. "+FilterAndSort.sortsType.values()[0].toString());
                for(int i=1;i<FilterAndSort.sortsType.values().length;i++){
                    String line = i+1 +". "+FilterAndSort.sortsType.values()[i].toString();
                    if (FilterAndSort.sortsType.values()[i] == sortsType){
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
                    if(sortDescendingMode == true) sortDescendingMode=false;
                    else sortDescendingMode = true;
                }
                else
                    sortsType = FilterAndSort.sortsType.values() [menuChanger-1];
                this.run();
            }
        };
    }

    private Menu showProducts() {
        return null;
    }

    private Menu showProduct() {
        return null;
    }

}
