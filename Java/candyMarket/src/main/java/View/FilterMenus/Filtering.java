package View.FilterMenus;

import Controller.GoodsManaging;
import Model.Filter;
import Model.FilterAndSort;
import View.ConsoleCmd;
import View.ConsoleDesign;
import View.Menu;
import View.ViewException;

import java.util.HashMap;

public class Filtering extends Menu{
    public Filtering(String name,Menu parentMenu) {
        super(name, parentMenu);
    }
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
}
