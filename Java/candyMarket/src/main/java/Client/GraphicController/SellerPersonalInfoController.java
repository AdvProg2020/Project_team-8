package Client.GraphicController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Client.Model.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SellerPersonalInfoController implements Initializable {
    @FXML private TableView<Category> tableView;
    @FXML private TableColumn<Category, String> nameColumn;
    @FXML private TableColumn<Category, String> specialAttributesColumn;

    @FXML private TableView<SellLog> sellLogTableView;
    @FXML private TableColumn<SellLog, HashMap<Good, Integer>> goodNameColumn;
    @FXML private TableColumn<SellLog, String> buyerNameColumn;
    @FXML private TableColumn<SellLog, Integer> saleAmountColumn;
    @FXML private TableColumn<SellLog, Integer> totalAmountColumn;

    @FXML private Label companyName;
    @FXML private Label balance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        balance.setText(Integer.toString(UserHandler.currentSeller.getBalance()));
        companyName.setText(UserHandler.currentSeller.getSellerCompanyName());

        nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        specialAttributesColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("specialAttributes"));
        tableView.setItems(getCategories());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        goodNameColumn.setCellValueFactory(new PropertyValueFactory<SellLog, HashMap<Good, Integer>>("goods"));
        buyerNameColumn.setCellValueFactory(new PropertyValueFactory<SellLog, String>("buyerName"));
        saleAmountColumn.setCellValueFactory(new PropertyValueFactory<SellLog, Integer>("saleAmount"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<SellLog, Integer>("totalAmount"));
        sellLogTableView.setItems(getCategoriesSellLog());
        sellLogTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private ObservableList<SellLog> getCategoriesSellLog() {
        ObservableList<SellLog> sellLogs = FXCollections.observableArrayList();
        sellLogs.addAll(UserHandler.currentSeller.getMySellLog());

        return sellLogs;
    }

    private ObservableList<Category> getCategories() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(ManageInfo.allCategories);

        return categories;
    }
}
