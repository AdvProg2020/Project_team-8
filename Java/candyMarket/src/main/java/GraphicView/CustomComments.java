package GraphicView;

import GraphicController.GoodMenuController;
import Model.Comment;
import Model.UserHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomComments extends VBox {
    Label user;
    Label content;
    Label reply;

    public CustomComments(Comment comment) {
        this.setPadding(new Insets(0, 0, 0, comment.getLeftInsect()));
        user = new Label();
        content = new Label();
        reply = new Label();
        user.setText("User: " + comment.getUser().getUsername());
        content.setText("Content: " + comment.getContent());
        reply.setText("Reply");
        reply.setTextFill(Color.BLUE);
        reply.setUnderline(true);
        user.setFont(Font.font("Times New Roman", 18));
        content.setFont(Font.font("Times New Roman", 18));
        reply.setFont(Font.font("Times New Roman", 16));
        reply.setCursor(Cursor.HAND);
        reply.setOnMouseClicked((e) -> {
            TextArea textArea = new TextArea();
            textArea.setPrefHeight(70.0);
            textArea.setPrefWidth(300);
            Button sendButton = new Button();
            sendButton.setPrefHeight(70.0);
            sendButton.setPrefWidth(70.0);
            sendButton.setText("Send");
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(0, 0, 0, 20));
            hBox.getChildren().addAll(textArea, sendButton);
            getChildren().add(hBox);
            sendButton.setOnAction(e1 -> {
                if (!UserHandler.isLogeIn()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Login First");
                    alert.show();
                } else if (textArea.getText().length() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Comment is empty");
                    alert.show();
                } else {
                    hBox.getChildren().clear();
                    hBox.getChildren().addAll(new CustomComments(new Comment(UserHandler.getCurrentUser(),
                            GoodMenuController.goodMenuController.getGood(), textArea.getText(), comment)));
                    getChildren().add(hBox);
                }
            });
        });
        setSpacing(5);
        getChildren().addAll(user, content, reply);
    }
}
