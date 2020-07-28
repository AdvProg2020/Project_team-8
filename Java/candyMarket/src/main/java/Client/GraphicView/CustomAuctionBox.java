package Client.GraphicView;

import BothUtl.PathHandler;
import Client.Model.Auction;
import Client.Model.Good;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.time.ZoneId;

public class CustomAuctionBox extends HBox {
    private Label endTime;
    private Label goodName;
    private Button enterAuction;

    public CustomAuctionBox(Auction auction) {
        this.setSpacing(20);
        this.setMinWidth(711);
        this.setMinHeight(100);
        endTime = new Label();
        goodName = new Label();
        enterAuction = new Button();
        endTime.setText("end time : " + Instant.ofEpochMilli(auction.getEndTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        goodName.setText(auction.getGood());
        enterAuction.setText("Enter");
        enterAuction.setTextFill(Color.WHITE);
        enterAuction.setStyle("-fx-background-color: #22226f");
        this.getChildren().addAll(goodName, endTime, enterAuction);
    }
}
