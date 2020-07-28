package Client.GraphicController;

import Client.Model.BuyLog;
import Client.Model.Good;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailLogController implements Initializable {
    @FXML private TableView<Good> goodTableView;
    @FXML private TableColumn<Good, String> nameColumn;
    @FXML private TableColumn<Good, String> brandColumn;
    @FXML private TableColumn<Good, Integer> priceColumn;
    @FXML private TableColumn<Good, Integer> scoreColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("averageScore"));

        goodTableView.setItems(getGoods());
    }

    private ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        BuyLog buyLog = ManagerPurchasedManagingController.managerPurchasedManagingController.getLog();
        goods.addAll(buyLog.getGoods());

        return goods;
    }
}
