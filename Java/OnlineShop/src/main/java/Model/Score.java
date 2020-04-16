package Model;

public class Score {
    private User user;
    private int score;

    public User getUser() {
        return user;
    }

    public Score(User user, int score, Good good) {
        this.user = user;
        this.score = score;
        this.good = good;
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

    public Score(User user) {
        this.user = user;
    }

    private Good good;
}
