package View.FilterAndSort;

import View.ConsoleCmd;
import View.ConsoleDesign;

public class ChoosePrice extends Filter {
    private int minPrice;
    private int maxPrice;
    public void run(){
        this.setEnable(true);
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Enter minimumPrice: ");
        minPrice = ConsoleCmd.scanner.nextInt();
        ConsoleDesign.printColorFull(ConsoleDesign.BLUE_UNDERLINED,"Enter maximumPrice: ");
        maxPrice = ConsoleCmd.scanner.nextInt();
    }
    public ChoosePrice(int id) {
        super(id);
    }
}
