package Model;

import java.util.ArrayList;


public class Category {
    private String name;
    private Category subCategory;
    private ArrayList<Good> goods;
    private ArrayList<String> specialAttributes;
    public static ArrayList<Category> categories;
}
