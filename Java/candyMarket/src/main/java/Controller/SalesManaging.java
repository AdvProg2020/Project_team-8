package Controller;

import Model.FilterAndSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SalesManaging {
    private static Scanner scanner;

    public static void setScanner(Scanner scanner) {
        SalesManaging.scanner = scanner;
    }

    public static ArrayList<String> viewCategories() {
        return null;
    }

    public static ArrayList<FilterAndSort> showAvailableFilters() {
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
