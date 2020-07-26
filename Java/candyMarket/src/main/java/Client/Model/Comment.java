package Client.Model;

import Client.Controller;
import Client.DataHandler.DataAccessor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Comment {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static enum OpinionSituation{
        CONFIRMING,CONFIRMED,NOT_CONFIRMED
    }
    @Id
    private int id;
    private String user;
    private String good;
    private String brand;
    @Enumerated(EnumType.STRING)
    private OpinionSituation situation;
    private static int mainOrder = 0;
    private double cmOrder;
    @Transient
    private int parentComment = -1;
    @Transient
    private int leftInsect;
    public Comment(){}
    public Comment(User user, Good good, String brand, Comment parentComment) {
        this.user = user.getUsername();
        this.good = good.getName();
        this.brand = brand;
        this.situation = OpinionSituation.CONFIRMED;
        if(parentComment!=null)this.parentComment = parentComment.getId();
        if (this.parentComment == -1) {
            leftInsect = 0;
            cmOrder = mainOrder++;
        } else {
            leftInsect = parentComment.leftInsect + 25;
            cmOrder = parentComment.cmOrder + 0.1;
        }
        this.id = ManageInfo.allComments.size();
        Controller.saveOrUpdateObject(this);
    }

    public static void sortComments() {
        for (int i = 0; i < ManageInfo.allComments.size(); i++) {
            for (int j = 0; j < ManageInfo.allComments.size() - 1; j++) {
                if (ManageInfo.allComments.get(j).cmOrder > ManageInfo.allComments.get(j + 1).cmOrder)
                    Collections.swap(ManageInfo.allComments, j, j+1);
            }
        }
    }

    public static List<Comment> getGoodsComments(Good good) {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : ManageInfo.allComments) {
            if (comment.getGood() == good)
                comments.add(comment);
        }
        return comments;
    }

    public int getLeftInsect() {
        return leftInsect;
    }

    public Comment getParentComment() {
        return Comment.getCommentById(parentComment);
    }
    public static Comment getCommentById(int id){
        for (Comment comment : ManageInfo.allComments) {
            if(comment.getId()==id) return comment;
        }
        return null;
    }
    public String getbrand() {
        return brand;
    }

    public void setbrand(String brand) {
        this.brand = brand;
    }

    public User getUser() {
        return User.getUserByUsername(user);
    }

    public void setUser(User user) {
        this.user = user.getUsername();
    }

    public Good getGood() {
        return Good.getGoodByName(good);
    }

    public void setGood(Good good) {
        this.good = good.getName();
    }

    public OpinionSituation getSituation() {
        return situation;
    }

    public void setSituation(OpinionSituation situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", brand='" + brand + '\'' +
                '}';
    }
}
