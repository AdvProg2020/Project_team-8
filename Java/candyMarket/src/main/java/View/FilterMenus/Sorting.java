package View.FilterMenus;

import Controller.GoodsManaging;
import Model.FilterAndSort;
import View.ConsoleCmd;
import View.ConsoleDesign;
import View.Menu;
import View.ViewException;

public class Sorting extends Menu{
    public Sorting(String name, Menu parentMenu) {
        super(name, parentMenu);
    }
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
        if(menuChanger == 0){
            GoodsManaging.updateFixedGoods();
            this.parentMenu.run();
        }
        else if(menuChanger == 1) {
            if(FilterAndSort.sortDescendingMode == true) FilterAndSort.sortDescendingMode=false;
            else FilterAndSort.sortDescendingMode = true;
        }
        else
            FilterAndSort.sortsType = FilterAndSort.sortsType.values() [menuChanger-1];
        this.run();
    }
}
