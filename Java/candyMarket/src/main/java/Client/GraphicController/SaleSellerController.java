package Client.GraphicController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Client.Model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SaleSellerController implements Initializable {
    public TableView<Good> tableView1;
    public TableColumn<Good, String> productColumn1;
    public TableColumn<Good, Integer> priceColumn1;
    public TableView<Good> tableView2;
    public TableColumn<Good, String> productColumn2;
    public TableColumn<Good, Integer> priceColumn2;
    public TextField salePercentAmount;
    public Button applyButton;
    public DatePicker startTime;
    public DatePicker endTime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable1();
    }

    private void createTable1() {
        productColumn1.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        priceColumn1.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));

        tableView1.setItems(getProducts());

        tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private ObservableList<Good> getProducts() {
        ObservableList<Good> products = FXCollections.observableArrayList();
        products.addAll(ManageInfo.allGoods);

        return products;
    }

    public void applySales(ActionEvent actionEvent) {
        ObservableList<Good> selectedProductsObservableList;
        selectedProductsObservableList = tableView1.getSelectionModel().getSelectedItems();
        List<Good> selectedProducts = new ArrayList<>();
        for (Good good : selectedProductsObservableList) {
            selectedProducts.add(good);
            System.out.println(good.getName());
        }
        Sale sale = new Sale(startTime.getValue(), endTime.getValue(), Integer.parseInt(salePercentAmount.getText()));
        sale.setGoods(selectedProducts);

        productColumn2.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        priceColumn2.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));

        tableView2.setItems(getProductsOnSale(sale));
    }

    private ObservableList<Good> getProductsOnSale(Sale sale) {
        ObservableList<Good> products = FXCollections.observableArrayList();
        products.addAll(sale.getGoods());

        return products;
    }

}
