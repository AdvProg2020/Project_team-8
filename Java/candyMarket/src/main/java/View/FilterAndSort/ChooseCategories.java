package View.FilterAndSort;

import Controller.GoodsManaging;
import Model.Category;
import View.ConsoleCmd;
import View.ConsoleDesign;
import View.ViewException;

import java.util.ArrayList;

public class ChooseCategories extends Filter {
    private ArrayList<SubFilter> categories = SubFilter.createAllSubFilters(GoodsManaging.viewCategories());
    public void run() throws ViewException {
        this.setEnable(true);
        System.out.println("Categories menu");
        System.out.println("0. back");
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Choose the categories : ");
        for (SubFilter s:
                categories) {
            String line = s.getId()+". "+s.getName();
            if(s.isEnable()) ConsoleDesign.printColorFull(ConsoleDesign.GREEN_BACKGROUND,line);
            else System.out.println(line);
        }
        int num = ConsoleCmd.scanner.nextInt();
        if(num==0) {
            return;
        }
        else {
            SubFilter currentSubFilter = SubFilter.getSubFilterById(num-1,categories);
            if(currentSubFilter.isEnable()) currentSubFilter.setEnable(false);
            else currentSubFilter.setEnable(true);
            run();
        }
    }
    public ChooseCategories(int id) {
        super(id);
    }
}
