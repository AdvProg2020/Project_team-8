package Model;

import java.util.ArrayList;


public class Category {
    private String name;
    private Category subCategory;
    private ArrayList<Good> goods;
    private ArrayList<String> specialAttributes;
    ArrayList<Category> categories = ManageInfo.allCategories;

    public Category(String name, ArrayList<Good> goods, ArrayList<String> specialAttributes) {
        this.name = name;
        this.goods = goods;
        this.specialAttributes = specialAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public ArrayList<String> getSpecialAttributes() {
        return specialAttributes;
    }

    public void setSpecialAttributes(ArrayList<String> specialAttributes) {
        this.specialAttributes = specialAttributes;
    }
}
