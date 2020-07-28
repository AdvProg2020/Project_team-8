package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import BothUtl.PathHandler;
import Client.GraphicView.PrimaryMenu;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainMenuController extends PrimaryMenu {
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buyerMenuMedia);
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
        buttons.add(MenuHandler.clientMenuBtn);
        buttons.add(MenuHandler.goodsMenuBtn);
        buttons.add(MenuHandler.auctionsMenuBtn);
        buttons.add(MenuHandler.exitBtn);
        return buttons;
    }
}
