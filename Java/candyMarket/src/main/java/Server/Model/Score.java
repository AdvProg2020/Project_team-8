package Server.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Score {
    @Id
    private int id;
    @ManyToOne
    private Buyer buyer;
    private int score;
    @ManyToOne
    private Good good;

    public Score(Buyer buyer, int score, Good good) {
        this.buyer = buyer;
        this.score = score;
        this.good = good;
        this.id = ManageInfo.allScores.size();
        ManageInfo.allScores.add(this);
    }
    public Score(){}
    public Buyer getUser() {
        return buyer;
    }

    public void setUser(Buyer buyer) {
        this.buyer = buyer;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
