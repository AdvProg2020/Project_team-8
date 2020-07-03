package Model;

import java.util.ArrayList;


public class Category {
    private String name;
    private Category subCategory;
    private ArrayList<Good> goods;
    private ArrayList<String> specialAttributes;
        public Category(String name, ArrayList<String> attributes) {
        this.name = name;
        specialAttributes = new ArrayList<>(){
            @Override
            public String toString() {
                String string="";
                for (String s:
                     this) {
                    string+=" | "+s;
                }
                return string;
            }
        };
        this.specialAttributes.addAll(attributes);
        ManageInfo.allCategories.add(this);
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

    public static Category getCategoryByName(String categoryName) {
        for (Category category : ManageInfo.allCategories) {
            if (category.getName().equals(categoryName))
                return category;
        }
        return null;
    }

    public static boolean isThisCategoryExist(String categoryName) {
        for (Category category : ManageInfo.allCategories) {
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