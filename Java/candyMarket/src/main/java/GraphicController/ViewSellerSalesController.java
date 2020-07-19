package GraphicController;

import Model.Good;
import Model.ManageInfo;
import Model.Sale;
import Model.UserHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSellerSalesController implements Initializable {
    public TableView<Sale> tableView;
    public TableColumn<Sale, Integer> salePercentColumn;
    public TableColumn<Sale, LocalDate> startTimeColumn;
    public TableColumn<Sale, LocalDate> endTimeColumn;
    public TableColumn<Sale, List<String>> productsOnSaleColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();
    }

    private void createTable() {
        salePercentColumn.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("salePercentageAmount"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<Sale, LocalDate>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<Sale, LocalDate>("endTime"));
        productsOnSaleColumn.setCellValueFactory(new PropertyValueFactory<Sale, List<String>>("productsOnSaleName"));

        tableView.setItems(getSales());
    }

    private ObservableList<Sale> getSales() {
        ObservableList<Sale> sales = FXCollections.observableArrayList();
        sales.addAll(UserHandler.currentSeller.getMySales());

        return sales;
    }
}
