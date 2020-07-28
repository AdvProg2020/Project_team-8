package Server.Model;



import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Auction {
    @Id
    private int id;
    private Long endTime;
    private String good;
    private String buyer;
    private int proposedMoney;
    @ElementCollection
    private List<String> texts;
    public static Auction currentAuction = null;
    public Auction(){}
    public Auction(Long endTime, String good) {
        this.endTime = endTime;
        this.good = good;
        ManageInfo.allAuctions.add(this);
        buyer = null;
        texts = new ArrayList<>();
        proposedMoney = -1;
    }
    public static Auction getAuctionById(int id){
        for (Auction auction : ManageInfo.allAuctions) {
            if (auction.getId() == id)
                return auction;
        }
        return null;
    }
    public List<String> getTexts() {
        return texts;
    }

    public void addTexts(String user, String text) {
        this.texts.add(user + "&" + text);
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getGood() {
        return good;
    }

    public int getProposedMoney() {
        return proposedMoney;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setProposedMoney(int proposedMoney) {
        this.proposedMoney = proposedMoney;
    }

    public boolean isTimeLeft() {
        return System.currentTimeMillis() - endTime < 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
