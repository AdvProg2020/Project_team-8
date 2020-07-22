package Server.Model;

import Client.DataHandler.DataAccessor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    private String name;
    @ElementCollection
    private List<String> goods;
    @ElementCollection
    private List<String> specialAttributes;
    public Category(){}
        public Category(String name, List<String> attributes) {
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
        this.goods = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Good> getGoods() {
        List<Good> goods = new ArrayList<>();
        for (String good : this.goods) {
            goods.add(Good.getGoodByName(good));
        }
        return goods;
    }

    public void setGoods(List<Good> goods) {
        List<String> goodStr = new ArrayList<>();
        for ( Good good : goods) {
            goodStr.add(good.getName());
        }
        this.goods = goodStr;
    }

    public List<String> getSpecialAttributes() {
        return specialAttributes;
    }

    public void setSpecialAttributes(List<String> specialAttributes) {
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