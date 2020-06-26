package GraphicController;

import Model.Good;
import Model.ManageInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsManagingController implements Initializable {
    @FXML private TableView<Good> tableView;
    @FXML private TableColumn<Good, String> productNameColumn;
    @FXML private TableColumn<Good, String> categoryColumn;
    @FXML private TableColumn<Good, Integer> priceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));

        tableView.setItems(getClients());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public ObservableList<Good> getClients()
    {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        goods.addAll(ManageInfo.allGoods);

        return goods;
    }

    public void deleteButtonPushed()
    {
        ObservableList<Good> selectedRows, allGoods;
        allGoods = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Good good: selectedRows)
        {
            Good.removeProduct(good);
            allGoods.remove(good);
        }
    }
}
