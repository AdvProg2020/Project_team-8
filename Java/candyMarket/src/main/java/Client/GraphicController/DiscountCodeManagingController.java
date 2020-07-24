package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import Client.Model.Discount;
import Client.Model.ManageInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DiscountCodeManagingController implements Initializable {
    public static DiscountCodeManagingController discountCodeManagingController;
    @FXML public   TableView<Discount> tableView;
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
        createDiscountTable();
    }
    public void createDiscountTable(){
        discountCodeManagingController = this;
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
        codes.addAll(ManageInfo.allDiscounts);

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
            String codeText =codeField.getText();
            Integer percentageText = Integer.parseInt(percentageField.getText());
            Integer maxAmountText = Integer.parseInt(maxAmountField.getText());
            Integer usageText = Integer.parseInt(usageField.getText());
            LocalDate initial = initializeDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            Discount newCode = new Discount(codeText, initial, end, percentageText, maxAmountText, usageText);

            tableView.getItems().add(newCode);
        }
    }


    public Discount getDiscount() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void userClickedOnTable()
    {
        this.detailedDiscountViewButton.setDisable(false);
    }

    public void editingOnClick(MouseEvent mouseEvent) {
        MenuHandler.createStageWithScene("DiscountCodeDetails");
    }
}
