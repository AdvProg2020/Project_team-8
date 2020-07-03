package GraphicController;

import Model.Buyer;
import Model.Discount;
import Model.ManageInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class showBuyersOfGoodController implements Initializable {
    public TableColumn<Buyer, String> userNameColumn;
    public TableColumn firstNameColumn;
    public TableColumn lastNameColumn;
    public TableColumn balanceColumn;
    @FXML private TableView<Buyer> buyersTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("userName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("firstName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Buyer, String>("balance"));
        buyersTableView.setItems(getBuyers());
    }

    public ObservableList<Buyer> getBuyers()
    {
        ObservableList<Buyer> buyers = FXCollections.observableArrayList();
        buyers.addAll((SellerProductHandlingController.sellerProductHandlingController.getTableView().getSelectionModel().getSelectedItem()
        .getBuyers()));
        return buyers;
    }
}
