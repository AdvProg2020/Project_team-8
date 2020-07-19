package Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Good {
    public long getDateModified() {
        return dateModified;
    }
    public void addScore(Score score){
        averageScore = averageScore*scores.size()+score.getScore()/(scores.size()+1);
        scores.add(score.getId());
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
    @Id
    private String name;
    private String brand;
    private int price;
    @ElementCollection
    private List<String> buyers;
    private String sellerName;
    private int stock;
    private String category;
    private String detailInfo;
    private int averageScore;
    @ElementCollection
    private List<String> specialAttributes;
    @ElementCollection
    private List<Integer> scores = new ArrayList<>() {
        @Override
        public String toString() {
            int a = 0;
            for (int score : scores) {
                a += Score.getScoreById(score).getScore();
            }
            return "" + a/scores.size();
        }
    };
    @ElementCollection
    private List<Integer> comments;
    private String image;
    private long dateCreated;
    public Good(){}
    public Good(String name, String brand, int price, Seller seller, int stock, Category category, String detailInfo, String image, List<String> specialAttributes) {
        this.specialAttributes = specialAttributes;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sellerName= seller.getUsername();
        this.stock = stock;
        this.category = category.getName();
        this.situation = ItemCreationSituation.CREATING_CHECK;
        this.detailInfo = detailInfo;
        this.salePercentageAmount = 0;
        this.averageScore = 0;
        this.dateModified = System.currentTimeMillis();
        this.buyers = new ArrayList<>();
        comments = new ArrayList<>();
        if (image!= null) this.image = image;
        this.dateCreated = System.currentTimeMillis();
    }


    public List<Buyer> getBuyers() {
        List<Buyer> orginalBuyers = new ArrayList<>();
        for (String buyer : buyers) {
            orginalBuyers.add((Buyer) Buyer.getUserByUsername(buyer));
        }
        return orginalBuyers;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return Category.getCategoryByName(category);
    }

    public void setCategory(Category category) {
        this.category = category.getName();
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public int getAverageScore() {
        if(scores.size()==0)
            return 0;
        int rate = 0;
        for (Integer score : scores) {
            rate+= Score.getScoreById(score).getScore();
        }
        return rate/scores.size();
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
        buyers.add(b.getUsername());
    }

    public static Good getGoodByName(String name, List<Good> goods) {
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

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        for (Integer comment : this.comments) {
            comments.add(Comment.getCommentById(comment));
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        List<Integer> cms = new ArrayList<>();
        for (Comment comment : comments) {
            cms.add(comment.getId());
        }
        this.comments = cms;
    }

    public List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        for (Integer score : this.scores) {
            scores.add(Score.getScoreById(score));
        }
        return scores;
    }

    public void setScores(List<Score> scores) {
        List<Integer> scoresStr = new ArrayList<>();
        for (Score score : scores) {
            scoresStr.add(score.getId());
        }
        this.scores = scoresStr;
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

    public List<String> getSpecialAttributes() {
        return specialAttributes;
    }

    public void setSpecialAttributes(List<String> specialAttributes) {
        this.specialAttributes = specialAttributes;
    }
}