package GraphicController;

import GraphicView.CustomComments;
import Model.Cart;
import Model.Comment;
import Model.Good;
import Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CommentsPageController implements Initializable {
    @FXML private ScrollPane scrollPane;
    @FXML private TextArea commentText;
    private VBox allComments;
    private static ArrayList<CustomComments> comments = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allComments = new VBox();
        allComments.setSpacing(5);
        List<Comment> goodsComments = Comment.getGoodsComments(GoodMenuController.goodMenuController.getGood());
        comments = createCommentBoxes(goodsComments);
        allComments.getChildren().addAll(comments);
        scrollPane.setContent(allComments);
    }

    private ArrayList<CustomComments> createCommentBoxes(List<Comment> goodsComments) {
        ArrayList<CustomComments> customComments = new ArrayList<>();
        for (Comment goodsComment : goodsComments) {
            customComments.add(new CustomComments(goodsComment));
        }
        return customComments;
    }


    public void sendComment(ActionEvent actionEvent) {
        if (!UserHandler.isLogeIn()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Login First");
            alert.show();
        } else if (commentText.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Comment is empty");
            alert.show();
        } else {
            allComments.getChildren().add(new CustomComments(new Comment(UserHandler.getCurrentUser(),
                    GoodMenuController.goodMenuController.getGood(), commentText.getText(), null)));
            scrollPane.setContent(allComments);
        }
    }
}
