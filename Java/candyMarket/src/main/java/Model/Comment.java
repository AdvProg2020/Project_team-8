package Model;

public class Comment {
    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public static enum OpinionSituation{
        CONFIRMING,CONFIRMED,NOT_CONFIRMED
    }
    private User user;
    private Good good;
    private String content;
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private OpinionSituation situation;

    public Comment(User user, Good good, String content,String title) {
        this.user = user;
        this.good = good;
        this.content = content;
        this.title = title;
        this.situation = OpinionSituation.CONFIRMED;
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

    public OpinionSituation getSituation() {
        return situation;
    }

    public void setSituation(OpinionSituation situation) {
        this.situation = situation;
    }
}
