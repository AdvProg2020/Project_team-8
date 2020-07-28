package Client.GraphicView;

import BothUtl.PathHandler;
import Client.Model.Auction;
import Client.Model.Good;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.Instant;
import java.time.ZoneId;

public class CustomAuctionBox extends HBox {
    private Label endTime;
    private Label goodName;
    private Label sellerName;
    private Button enterAuction;

    public CustomAuctionBox(Auction auction) {
        this.setSpacing(20);
        this.setMinWidth(711);
        this.setMinHeight(25);

        endTime = new Label();
        endTime.setText("end time: " + Instant.ofEpochMilli(auction.getEndTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        endTime.setFont(Font.font("System Bold", 20));
        endTime.setTextFill(Color.RED);

        goodName = new Label();
        goodName.setText(" " + auction.getGood());
        goodName.setFont(Font.font("System Bold", 20));
        goodName.setTextFill(Color.BLACK);

        sellerName = new Label();
        sellerName.setText("Seller: " + Good.getGoodByName(auction.getGood()).getSeller().getUsername());
        sellerName.setFont(Font.font("System Bold", 20));
        sellerName.setTextFill(Color.BLACK);

        enterAuction = new Button();
        enterAuction.setText("Enter");
        enterAuction.setTextFill(Color.WHITE);
        enterAuction.setStyle("-fx-background-color: #22226f");
        enterAuction.setOnAction(e -> {
            Auction.currentAuction = auction;
            MenuHandler.createStageWithScene("AuctionMenu");
        });

        HBox hBox1 = new HBox();
        hBox1.setMinHeight(25);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.setSpacing(20);
        hBox1.getChildren().addAll(goodName, sellerName, endTime);

        this.getChildren().addAll(hBox1, enterAuction);
    }
}
