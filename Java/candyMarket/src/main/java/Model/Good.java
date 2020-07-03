package Model;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;
public class Good {
    public long getDateModified() {
        return dateModified;
    }
    public void addScore(Score score){
        averageScore = averageScore*scores.size()+score.getScore()/(scores.size()+1);
        scores.add(score);
    }
    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }
    public int getPriceAfterSale(){
        return (int) (price*(1-(double)salePercentageAmount/100));
    }
    private long dateModified;
    private int salePercentageAmount;
    private int id;
    private ItemCreationSituation situation;
    private String name;
    private String brand;
    private int price;
    private ArrayList<Buyer> buyers;
    private String sellerName;
    private int stock;
    private Category category;
    private String detailInfo;
    private int averageScore;
    private ArrayList<Score> scores = new ArrayList<>() {
        @Override
        public String toString() {
            int a = 0;
            for (Score score : scores) {
                a += score.getScore();
            }
            return "" + a/scores.size();
        }
    };
    private ArrayList<Comment> comments;
    private String image;
    private long dateCreated;
    public Good(String name, String brand, int price, Seller seller, int stock, Category category, String detailInfo, String image) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sellerName= seller.getUsername();
        this.stock = stock;
        this.category = category;
        this.situation = ItemCreationSituation.CREATING_CHECK;
        this.detailInfo = detailInfo;
        this.salePercentageAmount = 0;
        this.averageScore = 0;
        this.dateModified = System.currentTimeMillis();
        comments = new ArrayList<>();
        if (image!= null) this.image = image;
        seller.addGood(this);
        this.dateCreated = System.currentTimeMillis();
    }


    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(ArrayList<Buyer> buyers) {
        this.buyers = buyers;
    }

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

    public boolean hasImage(){
        if(image!=null)
            return true;
        return false;
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

    public int getId() {
        return id;
    }

    public static boolean isThereGoodWithId(int goodId) {
        return true;
    }

    public static Good getGoodById(int goodId) {
        return null;
    }

    public void addBuyers(Buyer b){
        buyers.add(b);
    }

    public static Good getGoodByName(String name,ArrayList<Good> goods) {
        for (Good g:
                goods) {
            if(g.getName().equals(name))
                return g;
        }
        return null;
    }

    public int getSalePercentageAmount() {
        return salePercentageAmount;
    }

    public void setSalePercentageAmount(int salePercentageAmount) {
        this.salePercentageAmount = salePercentageAmount;
    }

    public Seller getSeller() {
        return (Seller) User.getUserByUsername(sellerName);
    }

    public void setSeller(Seller seller) {
        this.sellerName = seller.getUsername();
    }

    public String viewProductDetails() {
        return "ProductID: " + this.getId() + "\n" +
                "Situation: " + this.getSituation() + "\n" +
                "Name: " + this.getName() + "\n" +
                "Brand: " + this.getBrand() + "\n" +
                "Price: " + this.getPrice() + "\n" +
                "Seller: " + this.getSeller().viewCompanyInformation() +
                "Stock: " + this.getStock() + "\n" +
                "Category: " + this.getCategory().getName() + "\n" +
                "Detail info: " + this.detailInfo + "\n" +
                "Average score: " + this.averageScore + "\n" +
                "Opinions: " + this.getComments().toString() + "\n";
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public static void removeProduct(Good toNeRemovedGood) {
        for (Good good : ManageInfo.allGoods) {
            if (toNeRemovedGood.equals(good)) {
                ManageInfo.allGoods.remove(toNeRemovedGood);
            }
        }
    }

    @Override
    public String toString() {
        return "Good{" +
                "dateModified=" + dateModified +
                ", salePercentageAmount=" + salePercentageAmount +
                ", id=" + id +
                ", situation=" + situation +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", buyers=" + buyers +
                ", seller=" + sellerName +
                ", stock=" + stock +
                ", category=" + category +
                ", detailInfo='" + detailInfo + '\'' +
                ", averageScore=" + averageScore +
                ", scores=" + scores +
                ", comments=" + comments +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
    public static boolean isGoodWithName(String name){
        if(ManageInfo.allUsers.contains(name))
            return true;
        return false;
    }

}