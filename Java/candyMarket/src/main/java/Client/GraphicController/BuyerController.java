package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import BothUtl.PathHandler;
import Client.GraphicView.PrimaryMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyerController extends PrimaryMenu {
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buyerMenuMedia);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);

            }
        });
    }
    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons= new ArrayList<>();
        buttons.add(MenuHandler.viewProfileBtn);
        buttons.add(MenuHandler.viewBuyerPersonalInfoBtn);
        buttons.add(MenuHandler.auctionsMenuBtn);
        buttons.add(MenuHandler.fileBoughtBtn);
        buttons.add(MenuHandler.viewBuyerBuyLogBtn);
        buttons.add(MenuHandler.bankAccountBtn);
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }
}
