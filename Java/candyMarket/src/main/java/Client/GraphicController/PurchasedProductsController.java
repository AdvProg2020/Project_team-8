package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Client.Model.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PurchasedProductsController implements Initializable {
    public static PurchasedProductsController purchasedProductsController;
    @FXML private TableView<BuyLog> buyLogTableView;
    @FXML private TableColumn<String, HashMap<String, Integer>> productColumn;
    @FXML private TableColumn<String, Integer> priceColumn;
    @FXML private TableColumn<String, Integer> discountColumn;
    @FXML private TableColumn<String, CartSituation> situationColumn;

    @FXML private Button showLogButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        purchasedProductsController = this;

        showLogButton.setDisable(true);

        productColumn.setCellValueFactory(new PropertyValueFactory<String, HashMap<String, Integer>>("goods"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<String, Integer>("totalAmount"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<String, Integer>("discountAmount"));
        situationColumn.setCellValueFactory(new PropertyValueFactory<String, CartSituation>("buySituation"));

        buyLogTableView.setItems(getLog());
    }

    private ObservableList<BuyLog> getLog() {
        ObservableList<BuyLog> buyLogs = FXCollections.observableArrayList();
        buyLogs.addAll(UserHandler.currentBuyer.getMyLogs());

        return buyLogs;
    }

    public BuyLog getBuyLog() {
        return buyLogTableView.getSelectionModel().getSelectedItem();
    }

    public void scoringOnClick(ActionEvent mouseEvent) {
        MenuHandler.createStageWithScene("Score");
    }

    public void userClickedOnTable(MouseEvent mouseEvent) {
        showLogButton.setDisable(false);
    }
}
