package Model;

import Controller.GoodsManaging;
import View.FilterMenus.ChooseBrand;
import View.FilterMenus.ChooseCategories;
import View.FilterMenus.ChoosePrice;
import View.ViewException;

import java.util.ArrayList;
import java.util.Collections;

public class FilterAndSort {
    public static ArrayList<Filter> filters = FilterAndSort.createAllFilters();
    public static FilterAndSort.sortsTypes sortsType = sortsTypes.ALPHABETICALLY;
    public static Boolean sortDescendingMode = false;
    public static ArrayList<Filter> brands = FilterAndSort.createAllFilters(GoodsManaging.ViewBrands());
    public static ArrayList<Filter> categories = FilterAndSort.createAllFilters(GoodsManaging.viewCategories());
    public static int minPrice;
    public static int maxPrice;
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


    public static enum filtersTypes{
        IS_EXIST,CHOOSE_BRANDS,CHOOSE_PRICE_RANGE,CHOOSE_CATEGORIES
    }
    public static enum sortsTypes{
        DESCENDING_MODE,PRICE,ALPHABETICALLY,DATE_MODIFIED
    }
    public static ArrayList<Good> sortGoods(ArrayList<Good> goods){
        switch (sortsType) {
            case ALPHABETICALLY:
                Collections.sort(goods, new SortComparators.SortAlphabetically());
                break;
            case PRICE:
                Collections.sort(goods, new SortComparators.SortPrice());
                break;
            case DATE_MODIFIED:
                Collections.sort(goods, new SortComparators.SortDateModified());
                break;
            }
                if(sortDescendingMode)
                    Collections.reverse(goods);
                return  goods;
        }
    public static ArrayList<Good> filterGoods(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Filter f:
             filters) {
            if(f.isEnable()){
                if (filtersTypes.IS_EXIST.toString().equals(f.getName())) {
                    filteredGoods = isExistFilter(filteredGoods);
                } else if (filtersTypes.CHOOSE_BRANDS.toString().equals(f.getName())) {
                    filteredGoods = brandFilter(filteredGoods);
                } else if (filtersTypes.CHOOSE_CATEGORIES.toString().equals(f.getName())) {
                    filteredGoods = categoryFilter(filteredGoods);
                } else if (filtersTypes.CHOOSE_PRICE_RANGE.toString().equals(f.getName())) {
                    filteredGoods = priceFilter(filteredGoods);
                }
            }
        }
        return null;
    }
    public static ArrayList<Good> isExistFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
             goods) {
            if(g.getStock()==0)
                filteredGoods.remove(g);
        }
        return filteredGoods;
    }
    public static ArrayList<Good> brandFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(!isBrandOrCategory(g.getBrand(),brands))
                filteredGoods.remove(g);
        }
        return filteredGoods;
    }
    public static ArrayList<Good> categoryFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(!isBrandOrCategory(g.getCategory().getName(),categories))
                filteredGoods.remove(g);
        }
        return filteredGoods;
    }
    public static ArrayList<Good> priceFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(g.getPrice()>maxPrice || g.getPrice()<minPrice)
                filteredGoods.remove(g);
        }
        return filteredGoods;
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
    public static Boolean isBrandOrCategory(String name,ArrayList<Filter> items){
        for (Filter f:
             items) {
            if(f.getName()==name && f.isEnable()) return true;
        }
        return false;
    }

}
