package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Comment {
    public static enum OpinionSituation{
        CONFIRMING,CONFIRMED,NOT_CONFIRMED
    }

    private User user;
    private Good good;
    private String content;
    private OpinionSituation situation;
    private static int mainOrder = 0;
    private double order;
    private Comment parentComment;
    private int leftInsect;

    public Comment(User user, Good good, String content, Comment parentComment) {
        this.user = user;
        this.good = good;
        this.content = content;
        this.situation = OpinionSituation.CONFIRMED;
        this.parentComment = parentComment;
        if (this.parentComment == null) {
            leftInsect = 0;
            order = mainOrder++;
        } else {
            leftInsect = parentComment.leftInsect + 25;
            order = parentComment.order + 0.1;
        }
        ManageInfo.allComments.add(this);
    }

    public static void sortComments() {
        for (int i = 0; i < ManageInfo.allComments.size(); i++) {
            for (int j = 0; j < ManageInfo.allComments.size() - 1; j++) {
                if (ManageInfo.allComments.get(j).order > ManageInfo.allComments.get(j + 1).order)
                    Collections.swap(ManageInfo.allComments, j, j+1);
            }
        }
    }

    public static ArrayList<Comment> getGoodsComments(Good good) {
        ArrayList<Comment> comments = new ArrayList<>();
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
        return parentComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", content='" + content + '\'' +
                '}';
    }
}
