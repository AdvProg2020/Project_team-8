package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Score {
    private String user;
    private int score;
    private String good;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Id
    private int id;
    public Score(){}
    public Score(Buyer user, int score, Good good) {
        this.user = user.getUsername();
        this.score = score;
        this.good = good.getName();
        this.id= ManageInfo.allScores.size();
        Controller.saveOrUpdateObject(this);
    }
    public static Score getScoreById(int id){
        for (Score score : ManageInfo.allScores) {
            if(id==score.id)
                return score;
        }
        return null;
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
        return Good.getGoodByName(good);
    }
    public void setGood(Good good) {
        this.good = good.getName();
    }
}
