package View.FilterMenus;

import Model.Filter;
import Model.FilterAndSort;
import View.ConsoleCmd;
import View.ConsoleDesign;

public class ChoosePrice extends Filter {
    public void run(){
        this.setEnable(true);
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Enter minimumPrice: ");
        FilterAndSort.minPrice = ConsoleCmd.scanner.nextInt();
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Enter maximumPrice: ");
        FilterAndSort.maxPrice = ConsoleCmd.scanner.nextInt();
    }
    public ChoosePrice(int id) {
        super(id);
    }
}
