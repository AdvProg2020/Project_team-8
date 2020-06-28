package GraphicController;

import Model.Discount;
import Model.UserHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BuyerPersonalInfoController implements Initializable {
    @FXML public TableView<Discount> tableView;
    @FXML private TableColumn<Discount, Integer> codeColumn;
    @FXML private TableColumn<Discount, LocalDate> initialDateColumn;
    @FXML private TableColumn<Discount, LocalDate> endDateColumn;
    @FXML private TableColumn<Discount, Integer> percentageColumn;
    @FXML private TableColumn<Discount, Integer> maxAmountColumn;
    @FXML private TableColumn<Discount, Integer> usageTimeColumn;

    @FXML private Label balance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        balance.setText(Integer.toString(UserHandler.currentBuyer.getBalance()));

        codeColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("code"));
        initialDateColumn.setCellValueFactory(new PropertyValueFactory<Discount, LocalDate>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Discount, LocalDate>("endDate"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("percentReduction"));
        maxAmountColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("maxReductionAmount"));
        usageTimeColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("usageNumber"));

        tableView.setItems(getCodes());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public ObservableList<Discount> getCodes()
    {
        ObservableList<Discount> codes = FXCollections.observableArrayList();
        codes.addAll(UserHandler.currentBuyer.getMyDiscounts());

        return codes;
    }
}
