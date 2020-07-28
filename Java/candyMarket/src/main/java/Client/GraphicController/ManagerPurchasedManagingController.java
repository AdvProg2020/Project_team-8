package Client.GraphicController;

import Client.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Client.Model.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerPurchasedManagingController implements Initializable {


    @FXML private TableView<BuyLog> buyLogTableView;
    @FXML private TableColumn<BuyLog, String> productColumn;
    @FXML private TableColumn<BuyLog, Integer> priceColumn;
    @FXML private TableColumn<BuyLog, Integer> amountColumn;
    @FXML private TableColumn<BuyLog, String> addressColumn;
    @FXML private TableColumn<BuyLog, CartSituation> situationColumn;

    @FXML private TextField karmozdField;
    @FXML private TextField minWalletField;

    @FXML private ChoiceBox<CartSituation> situationChoiceBox;
    @FXML private Button changeSituationBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productColumn.setCellValueFactory(new PropertyValueFactory<BuyLog, String>(""));
        amountColumn.setCellValueFactory(new PropertyValueFactory<BuyLog, Integer>("totalAmount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<BuyLog, Integer>("discountAmount"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<BuyLog, String>("address"));
        situationColumn.setCellValueFactory(new PropertyValueFactory<BuyLog, CartSituation>("buySituation"));

        buyLogTableView.setItems(getLogs());

        situationChoiceBox.getItems().addAll(CartSituation.ON_THE_WAY, CartSituation.AT_DESTINATION);

        karmozdField.setText(String.valueOf(ManageInfo.allManagers.get(0).getWage()));
        minWalletField.setText(String.valueOf(ManageInfo.allManagers.get(0).getMinWalletMoney()));

        changeSituationBtn.setDisable(true);
        situationChoiceBox.setDisable(true);
    }

    private ObservableList<BuyLog> getLogs() {
        ObservableList<BuyLog> logs = FXCollections.observableArrayList();
        logs.addAll(ManageInfo.allBuyLogs);

        return logs;
    }

    public void setNumbers(ActionEvent actionEvent) {
        try {
            int karmozd = Integer.parseInt(karmozdField.getText());
            int minWalletMoney = Integer.parseInt(minWalletField.getText());
            if (karmozd < 0 || karmozd > 100 || minWalletMoney < 0)
                throw new NumberFormatException();
            ManageInfo.allManagers.get(0).setWage(karmozd);
            ManageInfo.allManagers.get(0).setMinWalletMoney(minWalletMoney);
            Controller.saveOrUpdateObject(ManageInfo.allManagers.get(0));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Successfully Changed");
            alert.show();
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("enter valid numbers");
            alert.show();
        }
    }

    public void changeSendingSituation(ActionEvent actionEvent) {
        if (situationChoiceBox.getValue() == CartSituation.AT_DESTINATION) {
            BuyLog log = buyLogTableView.getSelectionModel().getSelectedItem();
            log.setBuySituation(CartSituation.AT_DESTINATION);
            Controller.saveOrUpdateObject(log);
            buyLogTableView.setItems(getLogs());
            changeSituationBtn.setDisable(true);
            situationChoiceBox.setDisable(true);
        }
    }

    public void serClickedOnTable(MouseEvent mouseEvent) {
        BuyLog log = buyLogTableView.getSelectionModel().getSelectedItem();
        if (log.getBuySituation() == CartSituation.ON_THE_WAY) {
            situationChoiceBox.setDisable(false);
            changeSituationBtn.setDisable(false);
            situationChoiceBox.setValue(CartSituation.ON_THE_WAY);
        } else {
            situationChoiceBox.setValue(CartSituation.AT_DESTINATION);
            situationChoiceBox.setDisable(true);
            changeSituationBtn.setDisable(true);
        }
    }
}
