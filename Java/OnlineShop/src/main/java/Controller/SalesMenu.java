package Controller;

import Model.FilterAndSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SalesMenu {
    private static Scanner scanner;

    public static void setScanner(Scanner scanner) {
        SalesMenu.scanner = scanner;
    }

    public ArrayList<String> viewCategories() {
        return null;
    }

    public ArrayList<FilterAndSort> showAvailableFilters() {
        return null;
    }

    public boolean setFilter() {
        return false;
    }

    public ArrayList<FilterAndSort> showCurrentFilters() {
        return null;
    }

    public boolean disableAllFilters() {
        return false;
    }

    public ArrayList<FilterAndSort> showAvailableSorts() {
        return null;
    }

    public boolean setSort() {
        return false;
    }

    public ArrayList<FilterAndSort> showCurrentSort() {
        return null;
    }

    public boolean disableSort() {
        return false;
    }

    public HashMap<Integer, String> showProducts() {
        return null;
    }
}
