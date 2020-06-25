package Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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
    private Seller seller;
    private int stock;
    private Category category;
    private String categorySpecialAttributes;
    private String detailInfo;
    private int averageScore;
    private ArrayList<Score> scores = new ArrayList<>();
    private ArrayList<Comment> comments;
    public static ArrayList<Good> fixedGoods = new ArrayList<Good>();
    public static ArrayList<Good> unconfirmedGoods = new ArrayList<>();
    public static ArrayList<Good> confirmedGoods = ManageInfo.allGoods;

    public Good(String name, String brand, int price, Seller seller, int stock, Category category, String categorySpecialAttributes, String detailInfo) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.seller= seller;
        this.stock = stock;
        this.category = category;
        this.situation = ItemCreationSituation.CREATING_CHECK;
        this.categorySpecialAttributes = categorySpecialAttributes;
        this.detailInfo = detailInfo;
        this.salePercentageAmount = 0;
        this.averageScore = 0;
        this.dateModified = System.currentTimeMillis();
        unconfirmedGoods.add(this);
        this.id = unconfirmedGoods.size();
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
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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
                "Category special attributes: " + this.getCategorySpecialAttributes() + "\n" +
                "Detail info: " + this.detailInfo + "\n" +
                "Average score: " + this.averageScore + "\n" +
                "Opinions: " + this.getComments().toString() + "\n";

    }

    public void confirmProduct() {
        confirmedGoods.add(this);
        this.id = confirmedGoods.size();
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

    public static void removeProduct(Good good) {
        for (Good unconfirmedGood : unconfirmedGoods) {
            if (unconfirmedGood == good) {
                unconfirmedGoods.remove(unconfirmedGood);
                break;
            }
        }
        for (Good confirmedGood : confirmedGoods) {
            if (confirmedGood == good) {
                confirmedGoods.remove(confirmedGood);
                break;
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
                ", seller=" + seller +
                ", stock=" + stock +
                ", category=" + category +
                ", categorySpecialAttributes='" + categorySpecialAttributes + '\'' +
                ", detailInfo='" + detailInfo + '\'' +
                ", averageScore=" + averageScore +
                ", scores=" + scores +
                ", comments=" + comments +
                '}';
    }
}