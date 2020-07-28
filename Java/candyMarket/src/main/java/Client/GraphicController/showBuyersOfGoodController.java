package Client.GraphicController;

import Client.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class showBuyersOfGoodController implements Initializable {
    @FXML private TableView<Buyer> buyersTableView;
    @FXML private TableColumn<Buyer, String> userNameColumn;
    @FXML private TableColumn<Buyer, String> firstNameColumn;
    @FXML private TableColumn<Buyer, String> lastNameColumn;
    @FXML private TableColumn<Buyer, String> emailColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("email"));

        buyersTableView.setItems(getBuyers());
    }

    public ObservableList<Buyer> getBuyers() {
        ObservableList<Buyer> buyers = FXCollections.observableArrayList();
        Good good = SellerProductHandlingController.sellerProductHandlingController.getGood();
        ArrayList<Buyer> buyers1 = new ArrayList<>();
        for (Buyer buyer : ManageInfo.allBuyers) {
            if (buyer.hasBought(good.getName()))
                buyers1.add(buyer);
        }
        buyers.addAll(buyers1);

        return buyers;
    }
}
