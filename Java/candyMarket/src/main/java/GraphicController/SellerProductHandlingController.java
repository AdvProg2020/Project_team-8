package GraphicController;

import GraphicView.MenuHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SellerProductHandlingController implements Initializable {
    public static SellerProductHandlingController sellerProductHandlingController;

    @FXML private TableView<Good> tableView;
    @FXML private TableColumn<Good, String> productNameColumn;
    @FXML private TableColumn<Good, String> categoryColumn;
    @FXML private TableColumn<Good, Integer> priceColumn;

    @FXML private TextField goodNameField;
    @FXML private TextField priceField;
    @FXML private TextField brandField;
    @FXML private TextField stockField;
    @FXML private TextArea details;
    @FXML private ChoiceBox<Category> categoryChoiceBox;

    @FXML private Label errorMessage;
    @FXML private Button showBuyersButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sellerProductHandlingController = this;
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));

        tableView.setItems(getGoods());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        showBuyersButton.setDisable(true);

        categoryChoiceBox.getItems().addAll(ManageInfo.allCategories);
    }

    public ObservableList<Good> getGoods()
    {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        goods.addAll(UserHandler.currentSeller.getMyGoods());

        return goods;
    }

    public void userClickedOnTable(MouseEvent mouseEvent) {
        showBuyersButton.setDisable(false);
    }

    public void deleteButtonPushed()
    {
        ObservableList<Good> selectedRows, allGoods;
        allGoods = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Good good: selectedRows)
        {
            Good.removeProduct(good);
            UserHandler.currentSeller.removeProduct(good);
            allGoods.remove(good);
        }
    }

    public void requestNewProduct(ActionEvent actionEvent) {
        try {
            String goodNameText = goodNameField.getText();
            String brandText = brandField.getText();
            int priceText = Integer.parseInt(priceField.getText());
            int stockText = Integer.parseInt(stockField.getText());
            Category categoryValue = categoryChoiceBox.getValue();
            String detailsText = details.getText();
            new Request(Request.requestType.CREATE_GOOD).createAddProductRequest(new Good(goodNameText, brandText ,priceText, UserHandler.currentSeller, stockText, categoryValue, detailsText, "a", null));
            //UserHandler.currentSeller.addGood(new Good(goodNameText, brandText ,priceText, UserHandler.currentSeller, stockText, categoryValue, detailsText));
            errorMessage.setStyle("-fx-background-color: #00ff00;");
            errorMessage.setText("Your request has been sent");
        }catch (Exception e) {
            errorMessage.setStyle("-fx-background-color: #ff0000;");
            errorMessage.setText("Enter All Fields Correctly");
        }
    }

    public void showBuyers(ActionEvent actionEvent) {
        Good good = tableView.getSelectionModel().getSelectedItem();
        errorMessage.setStyle("-fx-background-color: #dbf2ff;");
        errorMessage.setText(good.getBuyers().toString());
    }

    public Good getGood() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void editing(ActionEvent actionEvent) {
        MenuHandler.createStageWithScene("GoodEditing");
    }
}
