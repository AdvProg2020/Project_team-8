package Client.GraphicController;

import Client.GraphicView.DateTimePicker;
import Client.GraphicView.MenuHandler;
import Client.Model.Auction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AuctionSettingController implements Initializable {
    private DateTimePicker dateTimePicker;
    @FXML private VBox timePicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateTimePicker = new DateTimePicker();
        Button button = new Button("Set");
        button.setOnAction(e -> {
            LocalDateTime localDateTime = dateTimePicker.getDateTimeValue();
            long millis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (System.currentTimeMillis() - millis > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Time Has Already Passed!");
                alert.show();
            } else {
                new Auction(millis, SellerProductHandlingController.sellerProductHandlingController.getGood().getName());
                MenuHandler.secondCurrentWindow.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Auction added successfully1");
                alert.show();
            }
        });
        timePicker.getChildren().addAll(dateTimePicker, button);
    }
}
