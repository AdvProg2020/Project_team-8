package View.FilterAndSort;

import Model.Good;
import Model.Sale;
import View.ViewException;

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
    public static ArrayList<Filter> createAllFilters(ArrayList<String> options){
        ArrayList<Filter> filters = new ArrayList<Filter>();
        for (int i=0;i<options.size();i++){
            filters.add(new Filter(i+1,options.get(i)));
        }
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
    public ArrayList<Good> filterGoods(ArrayList<Good> goods,ArrayList<Filter> filters){

        return null;
    }
    public ArrayList<Sale> sortSales(ArrayList<Sale> sales){
        return null;
    }
    public ArrayList<Sale> filterSales(ArrayList<Sale> sales){
        return null;
    }
    public static Filter getFilterById(int id,ArrayList<Filter> filters) throws ViewException {
        for (Filter f:
                filters) {
            if(f.getId() == id) return f;
        }
        throw ViewException.invalidNumber();
    }

}
