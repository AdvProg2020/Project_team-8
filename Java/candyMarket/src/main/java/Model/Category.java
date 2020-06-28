package Model;

import java.util.ArrayList;


public class Category {
    private String name;
    private Category subCategory;
    private ArrayList<Good> goods;
    private String specialAttributes;
    static ArrayList<Category> categories = ManageInfo.allCategories;

    public Category(String name, String specialAttributes) {
        this.name = name;
        this.specialAttributes = specialAttributes;
        categories.add(this);
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

    public String getSpecialAttributes() {
        return specialAttributes;
    }

    public void setSpecialAttributes(String specialAttributes) {
        this.specialAttributes = specialAttributes;
    }

    public static Category getCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName))
                return category;
        }
        return null;
    }

    public static boolean isThisCategoryExist(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName))
                return true;
        }

        return false;
    }

    public static boolean isThisProductInCategory (Category category, String productName) {
        for (Good productInCategory : category.getGoods()) {
            if (productInCategory.getName().equals(productName))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}