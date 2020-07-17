package Server.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    private String name;
    @ElementCollection(targetClass = Good.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Category_Good_map")
    private List<Good> goods;
    @ElementCollection
    private List<String> specialAttributes;
    public Category(){}
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
        if(attributes!=null)
        this.specialAttributes.addAll(attributes);
        ManageInfo.allCategories.add(this);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Good> getGoods() {
            ArrayList<Good> goods = new ArrayList<>();
            goods.addAll(this.goods);
            return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public ArrayList<String> getSpecialAttributes() {
            ArrayList<String> specialAttributes = new ArrayList<>();
            specialAttributes.addAll(this.specialAttributes);
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