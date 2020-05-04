package Controller;

import Model.Category;
import Model.ManageInfo;
import Model.Sale;
import View.FilterAndSort.FilterAndSort;

import java.util.ArrayList;
import java.util.HashMap;
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
    public static ArrayList<String> showAvailableFilters() {
        return null;
    }

    public static boolean setFilter() {
        return false;
    }

    public static ArrayList<FilterAndSort> showCurrentFilters() {
        return null;
    }

    public static boolean disableAllFilters() {
        return false;
    }

    public static ArrayList<FilterAndSort> showAvailableSorts() {
        return null;
    }

    public static boolean setSort() {
        return false;
    }

    public static ArrayList<FilterAndSort> showCurrentSort() {
        return null;
    }

    public static boolean disableSort() {
        return false;
    }

    public static HashMap<Integer, String> showProducts() {
        return null;
    }
}
