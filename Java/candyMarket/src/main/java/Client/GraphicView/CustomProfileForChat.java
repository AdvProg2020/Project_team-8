package Client.GraphicView;

import Client.GraphicController.ChatPageController;
import Client.Model.ManageInfo;
import Client.Model.User;
import Client.Model.UserHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class CustomProfileForChat extends HBox {
    private Label username;
    private Circle circle;
    private HBox hBox1;
    private HBox hBox2;

    public CustomProfileForChat(User user) {
        username = new Label();
        circle = new Circle();
        username.setText(" " + user.getUsername());
        username.setFont(Font.font("System Bold", 18));
        circle.setFill(Color.GRAY);
        for (User onlineUser : ManageInfo.allUsers) {
            if (onlineUser.getUsername().equals(user.getUsername()) && onlineUser.isOnline())
                circle.setFill(Color.rgb(41, 203, 103));
        }
        circle.setRadius(10);
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox1.getChildren().addAll(username);
        hBox2.getChildren().add(circle);
        this.setMinHeight(25);
        this.setMaxHeight(25);
        hBox1.setMinWidth(164);
        hBox2.setMinWidth(25);
        hBox1.setMaxWidth(164);
        hBox2.setMaxWidth(25);
        hBox1.setMaxHeight(25);
        hBox1.setMinHeight(25);
        hBox2.setMaxHeight(25);
        hBox2.setMinHeight(25);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox2.setAlignment(Pos.CENTER);
        hBox1.setCursor(Cursor.HAND);
        hBox1.setOnMouseClicked((e) -> {
            ChatPageController.chatPageController.showWhoAmITalkingTo(this);
            ChatPageController.chatPageController.setMessages(user);
        });
        this.getChildren().addAll(hBox1, hBox2);
    }

    public void setBackgroundChosen() {
        hBox1.setStyle("-fx-background-color: #98c7ff;");
        hBox2.setStyle("-fx-background-color: #98c7ff;");
        this.setStyle("-fx-background-color: #98c7ff;");
    }

    public void setBackgroundUnChosen() {
        hBox1.setStyle("-fx-background-color: #ffffff;");
        hBox2.setStyle("-fx-background-color: #ffffff;");
        this.setStyle("-fx-background-color: #ffffff;");
    }
}
