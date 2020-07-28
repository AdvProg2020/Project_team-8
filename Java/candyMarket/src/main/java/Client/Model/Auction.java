package Client.Model;

public class Auction {
    private Long endTime;
    private String good;
    private String buyer;
    private int proposedMoney;

    public Auction(Long endTime, String good) {
        this.endTime = endTime;
        this.good = good;
        ManageInfo.allAuctions.add(this);
        buyer = null;
        proposedMoney = 0;
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
}
