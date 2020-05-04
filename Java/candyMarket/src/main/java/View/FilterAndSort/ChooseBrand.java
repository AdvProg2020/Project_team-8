package View.FilterAndSort;

import Model.ManageInfo;
import View.ConsoleCmd;
import View.ConsoleDesign;
import View.ViewException;

import java.util.ArrayList;

public class ChooseBrand extends Filter {
    private ArrayList<SubFilter> brands = SubFilter.createAllSubFilters(ManageInfo.allBrands);
    public void run() throws ViewException {
        this.setEnable(true);
        System.out.println("Brands menu");
        System.out.println("0. back");
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Choose the brands : ");
        for (SubFilter s:
             brands) {
            String line = s.getId()+". "+s.getName();
            if(s.isEnable()) ConsoleDesign.printColorFull(ConsoleDesign.GREEN_BACKGROUND,line);
            else System.out.println(line);
        }
        int num = ConsoleCmd.scanner.nextInt();
        if(num==0) {
            return;
        }
        else {
            SubFilter currentSubFilter = SubFilter.getSubFilterById(num-1,brands);
            if(currentSubFilter.isEnable()) currentSubFilter.setEnable(false);
            else currentSubFilter.setEnable(true);
            run();
        }
    }
    public ChooseBrand(int id) {
        super(id);
    }
}
