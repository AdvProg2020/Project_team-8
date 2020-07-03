package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PathHandler;
import GraphicView.PrimaryMenu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ManagerController extends PrimaryMenu {
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.managerMenuMedia);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
    }
    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(MenuHandler.viewProfileBtn);
        buttons.add(MenuHandler.clientManagingBtn);
        buttons.add(MenuHandler.discountManagingBtn);
        buttons.add(MenuHandler.productManagingBtn);
        buttons.add(MenuHandler.categoryManagingBtn);
        buttons.add(MenuHandler.requestsBtn);
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }
}
