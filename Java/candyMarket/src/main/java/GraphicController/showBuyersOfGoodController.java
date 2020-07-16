package GraphicController;

import Model.BuyLog;
import Model.Buyer;
import Model.Good;
import Model.ManageInfo;
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
import java.util.ResourceBundle;

public class showBuyersOfGoodController implements Initializable {
    public TableColumn<String, String> userNameColumn;
    public TableColumn firstNameColumn;
    public TableColumn lastNameColumn;
    public TableColumn balanceColumn;
    public Label label;
    @FXML private TableView<Buyer> buyersTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Good good = SellerProductHandlingController.sellerProductHandlingController.getTableView().getSelectionModel().getSelectedItem();
        label.setFont(Font.font("Verdana",40));
        for (Buyer buyer:
        ManageInfo.allBuyers) {
            for (BuyLog b:
                 buyer.getMyLogs()) {
                if(b.getGoods().contains(good)) {
                    label.setText(label.getText()+buyer.getFirstName()+"\n");
                }
            }
        }
    }

    public ObservableList<Buyer> getBuyers()
    {
        ObservableList<Buyer> buyers = FXCollections.observableArrayList();
        buyers.addAll((SellerProductHandlingController.sellerProductHandlingController.getTableView().getSelectionModel().getSelectedItem()
        .getBuyers()));
        return buyers;
    }
}
