package Client.Model;

import java.util.ArrayList;
import java.util.List;

public class Auction {
    private Long endTime;
    private String good;
    private String buyer;
    private int proposedMoney;
    private List<String> texts;
    public static Auction currentAuction = null;

    public Auction(Long endTime, String good) {
        this.endTime = endTime;
        this.good = good;
        ManageInfo.allAuctions.add(this);
        buyer = null;
        texts = new ArrayList<>();
        proposedMoney = 0;
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

    public void finalizePurchasing() {
        if (buyer != null) {
            //buy;
        } else {
            //idk;
        }
    }
}
