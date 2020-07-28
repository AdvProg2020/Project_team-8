package Client.GraphicController;

import Client.GraphicView.CustomChatMessages;
import Client.Model.Auction;
import Client.Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AuctionMenuController implements Initializable {
    @FXML private Label goodsNameLabel;

    @FXML private Label currentPriceLabel;
    @FXML private TextField setHigherPriceField;
    @FXML private Button offerButton;

    @FXML private TextArea messageArea;
    @FXML private Button sendButton;
    @FXML private ScrollPane messagesPane;
    private ArrayList<CustomChatMessages> chatBoxes = new ArrayList<>();
    private VBox allChats;

    @FXML private Label whoGotItLabel;
    @FXML private Label buyerGotItLabel;

    @FXML private Button refreshButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPriceLabel.setText(Integer.toString(Auction.currentAuction.getProposedMoney()));
        goodsNameLabel.setText(Auction.currentAuction.getGood());
        allChats = new VBox();
        allChats.setSpacing(5);
        chatBoxes = createChatBoxes();
        allChats.getChildren().addAll(allChats);
        messagesPane.setContent(allChats);
    }

    private ArrayList<CustomChatMessages> createChatBoxes() {
        ArrayList<CustomChatMessages> customChatMessages = new ArrayList<>();
        for (String text : Auction.currentAuction.getTexts()) {
            String username;
            String message;
            int index = text.indexOf('&');
            username = text.substring(0, index);
            message = text.substring(index + 1);
            customChatMessages.add(new CustomChatMessages(username, message));
        }
        return customChatMessages;
    }

    public void sendNewMessage(ActionEvent actionEvent) {
        String newText = messageArea.getText();
        if (newText.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Empty Message");
            alert.show();
        } else if (!Auction.currentAuction.isTimeLeft()) {
            Functions.showDialog("Auction Has Finished", true);
        } else {
            Auction.currentAuction.addTexts(UserHandler.currentBuyer.getUsername(), newText);
            allChats.getChildren().add(new CustomChatMessages(UserHandler.currentBuyer.getUsername(), newText));
            messagesPane.setContent(allChats);
        }
    }

    public void offerANewBid(ActionEvent actionEvent) {
        try {
            int newBid = Integer.parseInt(setHigherPriceField.getText());
            if (newBid <= Auction.currentAuction.getProposedMoney()) {
                Functions.showDialog("You Must Offer A Higher Bid", true);
            } else if (newBid > UserHandler.currentBuyer.getBalance()) {
                Functions.showDialog("You don't have enough money", true);
            } else {
                Auction.currentAuction.setBuyer(UserHandler.currentBuyer.getUsername());
                Auction.currentAuction.setProposedMoney(newBid);
                currentPriceLabel.setText(String.valueOf(Auction.currentAuction.getProposedMoney()));
            }
        } catch (NumberFormatException e) {
            Functions.showDialog("Wrong Format!", true);
        }
    }

    public void refreshing(ActionEvent actionEvent) {
        currentPriceLabel.setText(String.valueOf(Auction.currentAuction.getProposedMoney()));
        allChats = new VBox();
        allChats.setSpacing(5);
        chatBoxes = createChatBoxes();
        allChats.getChildren().addAll(allChats);
        messagesPane.setContent(allChats);
        if (!Auction.currentAuction.isTimeLeft()) {
            setHigherPriceField.setDisable(true);
            messageArea.setDisable(true);
            sendButton.setDisable(true);
            offerButton.setDisable(true);
            refreshButton.setDisable(true);
            whoGotItLabel.setVisible(true);
            buyerGotItLabel.setText(Auction.currentAuction.getBuyer());
            buyerGotItLabel.setVisible(true);
        }
    }
}
