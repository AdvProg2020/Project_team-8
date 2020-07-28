package Client.GraphicView;

import Client.Model.ManageInfo;
import Client.Model.User;
import Client.Model.UserHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class CustomOnlineUserBox extends HBox {
    private Label username;
    private Circle circle;

    public CustomOnlineUserBox(String user) {
        username = new Label();
        username.setText(" " + user);
        username.setFont(Font.font("System Bold", 18));
        circle = new Circle();
        circle.setRadius(10);
        this.setMinHeight(25);
        this.setMaxWidth(324);
        circle.setFill(Color.GRAY);
        for (User onlineUser : ManageInfo.allUsers) {
            if (onlineUser.getUsername().equals(user) && onlineUser.isOnline())
                circle.setFill(Color.rgb(41, 203, 103));
        }
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox2.setAlignment(Pos.CENTER);
        hBox1.setMinHeight(25);
        hBox2.setMinHeight(25);
        hBox1.setMinWidth(299);
        hBox2.setMinWidth(25);
        hBox1.getChildren().addAll(username);
        hBox2.getChildren().addAll(circle);
        this.getChildren().addAll(hBox1, hBox2);
    }
}
