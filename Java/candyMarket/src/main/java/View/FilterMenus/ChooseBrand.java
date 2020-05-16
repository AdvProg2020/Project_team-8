package View.FilterMenus;

import Model.Filter;
import Model.FilterAndSort;
import View.ConsoleCmd;
import View.ConsoleDesign;
import View.ViewException;

public class ChooseBrand extends Filter {
    public void run() throws ViewException {
        this.setEnable(true);
        System.out.println("Brands menu");
        System.out.println("0. back");
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Choose the brands : ");
        for (Filter s:
             FilterAndSort.brands) {
            String line = s.getId()+". "+s.getName();
            if(s.isEnable()) ConsoleDesign.printColorFull(ConsoleDesign.GREEN_BACKGROUND,line);
            else System.out.println(line);
        }
        int num = ConsoleCmd.scanner.nextInt();
        if(num==0) {
            return;
        }
        else {
            if (num>FilterAndSort.brands.size()) {
                System.out.println(ViewException.invalidNumber().getMessage());
                run();
            }
            Filter currentFilter = FilterAndSort.getFilterById(num-1,FilterAndSort.brands);
            if(currentFilter.isEnable()) currentFilter.setEnable(false);
            else currentFilter.setEnable(true);
            run();
        }
    }
    public ChooseBrand(int id) {
        super(id);
    }
}
