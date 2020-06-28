package GraphicController;

import Model.Category;
import Model.Good;
import Model.ManageInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GoodEditingController implements Initializable {
    private Good good;

    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;
    @FXML private TextField detailField;
    @FXML private ChoiceBox<Category> categoryChoiceBox;

    @FXML private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        good = SellerProductHandlingController.sellerProductHandlingController.getGood();

        nameField.setText(good.getName());
        brandField.setText(good.getBrand());
        priceField.setText(Integer.toString(good.getPrice()));
        stockField.setText(Integer.toString(good.getStock()));
        detailField.setText(good.getDetailInfo());
        categoryChoiceBox.getItems().addAll(ManageInfo.allCategories);

        errorMessage.setText("");
    }


    public void editing(ActionEvent actionEvent) {
        try {
            int priceText = Integer.parseInt(priceField.getText());
            int stockText = Integer.parseInt(stockField.getText());
            String nameText = nameField.getText();
            String brandText = brandField.getText();
            String detailText = detailField.getText();
            Category categoryValue = categoryChoiceBox.getValue();
            errorMessage.setStyle("-fx-background-color: #00ff00;");
            errorMessage.setText("Update Request has been sent");
            good.setName(nameText);
            good.setBrand(brandText);
            good.setPrice(priceText);
            good.setStock(stockText);
            good.setDetailInfo(detailText);
            good.setCategory(categoryValue);
        }catch (Exception e) {
            errorMessage.setStyle("-fx-background-color: #ff0000;");
            errorMessage.setText("Invalid Change!");
        }
    }
}
