package Client.GraphicController;

import Client.GraphicView.CustomAuctionBox;
import Client.Model.Auction;
import Client.Model.ManageInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuctionsMenuController implements Initializable {
    @FXML private ScrollPane scrollPane;

    private ArrayList<CustomAuctionBox> auctionBoxes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox allAuctions = new VBox();
        allAuctions.setSpacing(5);
        auctionBoxes = createAuctionBoxes();
        allAuctions.getChildren().addAll(auctionBoxes);
        scrollPane.setContent(allAuctions);
    }

    private ArrayList<CustomAuctionBox> createAuctionBoxes() {
        for (int i = 0; i < ManageInfo.allAuctions.size(); i++) {
            if (!ManageInfo.allAuctions.get(i).isTimeLeft()) {
                ManageInfo.allAuctions.get(i).finalizePurchasing();
                ManageInfo.allAuctions.remove(i);
                i--;
            }
        }
        ArrayList<CustomAuctionBox> customAuctionBoxes = new ArrayList<>();
        for (Auction allAuction : ManageInfo.allAuctions) {
            customAuctionBoxes.add(new CustomAuctionBox(allAuction));
        }
        return customAuctionBoxes;
    }
}
