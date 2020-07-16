package Server.Model;

public class Score {
    private String user;
    private int score;
    private String good;

    public Score(Buyer user, int score, Good good) {
        this.user = user.getUsername();
        this.score = score;
        this.good = good.getName();
    }

    public Buyer getUser() {
        return (Buyer) Buyer.getUserByUsername(user);
    }

    public void setUser(Buyer user) {
        this.user = user.getUsername();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Good getGood() {
        return Good.getGoodByName(good, ManageInfo.allGoods);
    }
    public void setGood(Good good) {
        this.good = good.getName();
    }
}
