package Client.GraphicController;

import Client.GraphicView.CustomOnlineUserBox;
import Client.Model.ManageInfo;
import Client.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OnlineUsersShowController implements Initializable {
    @FXML private ScrollPane scrollPane;

    private ArrayList<CustomOnlineUserBox> onlineUsers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        onlineUsers = createBoxes();
        vBox.getChildren().addAll(onlineUsers);
        scrollPane.setContent(vBox);
    }

    private ArrayList<CustomOnlineUserBox> createBoxes() {
        ArrayList<CustomOnlineUserBox> customOnlineUserBoxes = new ArrayList<>();
        for (User user : ManageInfo.allUsers) {
            customOnlineUserBoxes.add(new CustomOnlineUserBox(user.getUsername()));
        }
        return customOnlineUserBoxes;
    }


}
