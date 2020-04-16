package Model;

public class Score {
    private User user;
    private int score;
    private Good good;

    public Score(User user, int score, Good good) {
        this.user = user;
        this.score = score;
        this.good = good;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
