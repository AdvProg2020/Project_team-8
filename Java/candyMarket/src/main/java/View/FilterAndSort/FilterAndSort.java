package View.FilterAndSort;

import Model.Good;
import Model.Sale;

import java.util.ArrayList;

public class FilterAndSort {
    public static ArrayList<Filter> createAllFilters(){
        ArrayList<Filter> filters = new ArrayList<Filter>();
        filters.add(new Filter(1));
        filters.add(new ChooseBrand(2));
        filters.add(new ChoosePrice(3));
        filters.add(new ChooseCategories(4));
        return filters;
    }
    public static enum filtersType{
        IS_EXIST,CHOOSE_BRANDS,CHOOSE_PRICE_RANGE,CHOOSE_CATEGORIES
    }
    public static enum sortsType{
        DESCENDING_MODE,PRICE,ALPHABETICALLY,CHOOSE_CATEGORY,DATE_MODIFIED
    }
    public ArrayList<Good> sortGoods(ArrayList<Good> goods, ArrayList<sortsType> sortsTypes){
        return null;
    }
    public ArrayList<Good> filterGoods(ArrayList<Good> goods,ArrayList<filtersType> filtersTypes){
        return null;
    }
    public ArrayList<Sale> sortSales(ArrayList<Sale> sales){
        return null;
    }
    public ArrayList<Sale> filterSales(ArrayList<Sale> sales){
        return null;
    }
}
