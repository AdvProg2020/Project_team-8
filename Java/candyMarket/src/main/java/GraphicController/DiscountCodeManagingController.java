package GraphicController;

import Model.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class DiscountCodeManagingController implements Initializable {
    @FXML private TableView<Discount> tableView;
    @FXML private TableColumn<Discount, Integer> codeColumn;
    @FXML private TableColumn<Discount, LocalDate> initialDateColumn;
    @FXML private TableColumn<Discount, LocalDate> endDateColumn;
    @FXML private TableColumn<Discount, Integer> percentageColumn;
    @FXML private TableColumn<Discount, Integer> maxAmountColumn;
    @FXML private TableColumn<Discount, Integer> usageTimeColumn;

    @FXML private TextField codeField;
    @FXML private TextField percentageField;
    @FXML private TextField maxAmountField;
    @FXML private TextField usageField;
    @FXML private DatePicker initializeDatePicker;
    @FXML private DatePicker endDatePicker;

    @FXML private Button detailedDiscountViewButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("code"));
        initialDateColumn.setCellValueFactory(new PropertyValueFactory<Discount, LocalDate>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Discount, LocalDate>("endDate"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("percentReduction"));
        maxAmountColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("maxReductionAmount"));
        usageTimeColumn.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("usageNumber"));

        tableView.setItems(getCodes());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.detailedDiscountViewButton.setDisable(true);
    }

    public ObservableList<Discount> getCodes()
    {
        ObservableList<Discount> codes = FXCollections.observableArrayList();
        codes.addAll(Discount.getDiscounts());

        return codes;
    }

    public void deleteButtonPushed(ActionEvent actionEvent) {
        ObservableList<Discount> selectedRows, allCodes;
        allCodes = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Discount discount: selectedRows)
        {
            Discount.removeCode(discount);
            allCodes.remove(discount);
        }
    }

    public void newCodeButtonPushed()
    {
        if (codeField.getText().length() > 0 && percentageField.getText().length() > 0 &&
        maxAmountField.getText().length() > 0 && usageField.getText().length() > 0 &&
        initializeDatePicker.getValue() != null  && endDatePicker.getValue() != null) {
            Integer codeText = Integer.parseInt(codeField.getText());
            Integer percentageText = Integer.parseInt(percentageField.getText());
            Integer maxAmountText = Integer.parseInt(maxAmountField.getText());
            Integer usageText = Integer.parseInt(usageField.getText());
            LocalDate initial = initializeDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            Discount newCode = new Discount(codeText, initial, end, percentageText, maxAmountText, usageText);

            tableView.getItems().add(newCode);
        }
    }

    public void userClickedOnTable()
    {
        this.detailedDiscountViewButton.setDisable(false);
    }
}
