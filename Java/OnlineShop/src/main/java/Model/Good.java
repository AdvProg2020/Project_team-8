package Model;

import java.util.ArrayList;

public class Good {
    private int id;
    private ItemCreationSituation situation;
    private String name;
    private String brand;
    private int price;
    private ArrayList<User> sellers;
    private ArrayList<Buyer> buyers;
    private int stock;
    private Category category;
    private String categorySpecialAttributes;
    private String detailInfo;
    private int averageScore;
    private ArrayList<Opinion> opinions;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategorySpecialAttributes() {
        return categorySpecialAttributes;
    }

    public void setCategorySpecialAttributes(String categorySpecialAttributes) {
        this.categorySpecialAttributes = categorySpecialAttributes;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(ArrayList<Opinion> opinions) {
        this.opinions = opinions;
    }

    public ItemCreationSituation getSituation() {
        return situation;
    }

    public void setSituation(ItemCreationSituation situation) {
        this.situation = situation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Good(String name, String brand, int price, ArrayList<User> sellers, int stock, Category category, String categorySpecialAttributes, String detailInfo) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sellers = sellers;
        this.stock = stock;
        this.category = category;
        this.categorySpecialAttributes = categorySpecialAttributes;
        this.detailInfo = detailInfo;
    }

    public static boolean isThereGoodWithId(int goodId) {
        return true;
    }

    public static Good getGoodById(int goodId) {
        return null;
    }


    public int getId() {
        return id;
    }
}
