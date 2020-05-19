package Model;

public class Score {
    private Buyer user;
    private int score;
    private Good good;

    public Score(Buyer user, int score, Good good) {
        this.user = user;
        this.score = score;
        this.good = good;
    }

    public User getUser() {
        return user;
    }

    public void setUser(Buyer user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}
