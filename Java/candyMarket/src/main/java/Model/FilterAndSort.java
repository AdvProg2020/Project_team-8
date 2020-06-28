package Model;

import java.util.ArrayList;
import java.util.Collections;

public class FilterAndSort {
    public static FilterAndSort.sortsTypes sortsType = sortsTypes.DATE_CREATED;
    public static Boolean sortDescendingMode = false;
    public static ArrayList<String> brands = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static int minPrice;
    public static int maxPrice;
    public static enum filtersTypes{
        IS_EXIST,CHOOSE_BRANDS,CHOOSE_PRICE_RANGE,CHOOSE_CATEGORIES
    }
    public static enum sortsTypes{
        PRICE,ALPHABETICALLY,DATE_MODIFIED,DATE_CREATED
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
            case DATE_CREATED:
                Collections.sort(goods, new SortComparators.SortDateCreated());
                break;
            }
                if(sortDescendingMode)
                    Collections.reverse(goods);
                return  goods;
        }
        public static ArrayList<String> getSortsType(){
            ArrayList<String> sorts = new ArrayList<>();
            for (sortsTypes s:
                 sortsTypes.values()) {
                sorts.add(s.toString());
            }
            return sorts;
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
            if(brands.contains(g.getBrand()))
                filteredGoods.add(g);
        }
        return filteredGoods;
    }
    public static ArrayList<Good> categoryFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(categories.contains(g.getCategory()))
                filteredGoods.add(g);
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


}
