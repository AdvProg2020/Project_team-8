package GraphicView;

import Model.UserHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomChatMessages extends VBox {
    Label user;
    Label text;

    public CustomChatMessages(String username, String chat) {
        user = new Label();
        text = new Label();
        this.setStyle("-fx-background-color: #2f2f6f; -fx-padding: 15;");
        this.setMinWidth(555);
        this.setMaxWidth(555);
        user.setText("User: " + username);
        text.setText("Text: \n       " + chat);
        user.setTextFill(Color.LIGHTSTEELBLUE);
        text.setTextFill(Color.WHITE);
        user.setFont(Font.font("Times New Roman", 18));
        text.setFont(Font.font("Times New Roman", 18));
        setSpacing(5);
        getChildren().addAll(user, text);
    }
}
