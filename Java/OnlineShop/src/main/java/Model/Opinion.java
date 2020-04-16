package Model;

public class Opinion {
    enum OpinionSituation{
        CONFIRMING,CONFIRMED,NOT_CONFIRMED
    }
    private User user;
    private Good good;

    public Opinion(User user, Good good, String commentText, boolean wasBoughtByUser) {
        this.user = user;
        this.good = good;
        this.commentText = commentText;
        this.wasBoughtByUser = wasBoughtByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public boolean isWasBoughtByUser() {
        return wasBoughtByUser;
    }

    public void setWasBoughtByUser(boolean wasBoughtByUser) {
        this.wasBoughtByUser = wasBoughtByUser;
    }

    public OpinionSituation getSituation() {
        return situation;
    }

    public void setSituation(OpinionSituation situation) {
        this.situation = situation;
    }

    private String commentText;
    private boolean wasBoughtByUser;
    private OpinionSituation situation;
}
