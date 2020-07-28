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

public class SellerController extends PrimaryMenu {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.sellerMenuMedia);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);

            }
        });
    }
    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(MenuHandler.viewProfileBtn);
        buttons.add(MenuHandler.viewSellerPersonalInfoBtn);
        buttons.add(MenuHandler.sellerProductManagingBtn);
        buttons.add(MenuHandler.sellerRequestsBtn);
        buttons.add(MenuHandler.sellerCreateSaleBtn);
        buttons.add(MenuHandler.sellerShowSaleBtn);
        buttons.add(MenuHandler.sellerBalanceHandlingBtn);
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }
}
