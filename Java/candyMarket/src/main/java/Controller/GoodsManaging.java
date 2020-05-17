package Controller;

import Model.Category;
import Model.Good;
import Model.ManageInfo;
import Model.FilterAndSort;

import java.util.ArrayList;
import java.util.Scanner;

public class GoodsManaging {
    private static Scanner scanner;

    public static void setScanner(Scanner scanner) {
        GoodsManaging.scanner = scanner;
    }

    public static ArrayList<String> viewCategories() {
        ArrayList<String> categories = new ArrayList<String>();
        for (Category c:
                ManageInfo.allCategories) {
            categories.add(c.getName());
        }
        return categories;
    }
    public static ArrayList<String> ViewBrands() {
        return ManageInfo.allBrands;
    }
    public static void updateFixedGoods(){
        Good.fixedGoods = FilterAndSort.filterGoods(ManageInfo.allGoods);
        Good.fixedGoods = FilterAndSort.sortGoods(Good.fixedGoods);
    }
    public static ArrayList<String> showProducts() {
        ArrayList<String> viewGoods = new ArrayList<String>();
        for (Good g:
                Good.fixedGoods) {
            viewGoods.add(g.getName());
        }
        return viewGoods;
    }
}
