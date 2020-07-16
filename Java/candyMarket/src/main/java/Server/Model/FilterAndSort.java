package Server.Model;

import java.util.ArrayList;
import java.util.Collections;

public class FilterAndSort {
    public static FilterAndSort.sortsTypes sortsType = sortsTypes.DATE_CREATED;
    public static Boolean sortDescendingMode = false;
    public static ArrayList<String> brandsFilter = new ArrayList<>();
    public static ArrayList<Category> categoriesFilter  = new ArrayList<>();
    public static int minPriceFilter = 0;
    public static int maxPriceFilter = 10000;
    public static boolean isBrandFilterOn = false;
    public static boolean isCategoryFilterOn = false;
    public static boolean isPriceFilterOn = false;
    public static boolean isAvailableFilterOn = false;
    public static boolean isOffFilterOn = false;
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
    public static ArrayList<Good> availableProductsFilter(ArrayList<Good> goods){
        for (Good g:
             goods) {
            if(g.getStock()==0)
                goods.remove(g);
        }
        return goods;
    }
    public static ArrayList<Good> offProductsFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(g.getSalePercentageAmount()==0)
                filteredGoods.remove(g);
        }
        return goods;
    }
    public static ArrayList<Good> brandFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(brandsFilter.contains(g.getBrand()))
                filteredGoods.add(g);
        }
        return filteredGoods;
    }
    public static ArrayList<Good> categoryFilter(ArrayList<Good> goods){
        ArrayList<Good> filteredGoods = new ArrayList<Good>();
        for (Good g:
                goods) {
            if(categoriesFilter.contains(g.getCategory()))
                filteredGoods.add(g);
        }
        return filteredGoods;
    }
    public static ArrayList<Good> priceFilter(ArrayList<Good> goods){
        for (int i = 0; i <goods.size() ; i++) {
            if(goods.get(i).getPrice()>maxPriceFilter || goods.get(i).getPrice()<minPriceFilter)
                goods.remove(goods.get(i));
        }
        return goods;
    }
    public ArrayList<Sale> sortSales(ArrayList<Sale> sales){
        return null;
    }
    public ArrayList<Sale> filterSales(ArrayList<Sale> sales){
        return null;
    }


}
