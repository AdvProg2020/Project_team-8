package Client.GraphicController;

import Client.Model.BuyLog;
import Client.Model.Good;
import Client.Model.Score;
import Client.Model.UserHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {
    @FXML private TableView<Good> goodTableView;
    @FXML private TableColumn<Good, String> nameColumn;
    @FXML private TableColumn<Good, String> brandColumn;
    @FXML private TableColumn<Good, Integer> priceColumn;
    @FXML private TableColumn<Good, Integer> scoreColumn;

    @FXML private TextField scoreField;
    @FXML private Button scoreButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreField.setDisable(true);
        scoreButton.setDisable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("averageScore"));

        goodTableView.setItems(getGoods());
    }

    private ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        BuyLog buyLog = PurchasedProductsController.purchasedProductsController.getBuyLog();
        goods.addAll(buyLog.getGoods());

        return goods;
    }

    public void userClickedOnTable(MouseEvent mouseEvent) {
        scoreField.setDisable(false);
        scoreButton.setDisable(false);
    }

    public void addScore(ActionEvent actionEvent) {
        Good good = goodTableView.getSelectionModel().getSelectedItem();
        if (good == null) {
            Functions.showDialog("select a row first", true);
        } else {
            int score = Integer.parseInt(scoreField.getText());
            UserHandler.currentBuyer.rate(good, score);
            Functions.showDialog("scored successfully", false);
        }
    }
}
