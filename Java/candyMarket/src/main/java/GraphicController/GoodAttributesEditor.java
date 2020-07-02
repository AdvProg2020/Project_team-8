package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PathHandler;
import Model.Category;
import Model.Good;
import Model.ManageInfo;
import Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GoodAttributesEditor implements Initializable {
    public ImageView goodImg;
    private Good good;

    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;
    @FXML private TextField detailField;
    @FXML private ChoiceBox<Category> categoryChoiceBox;

    @FXML private Label errorMessage;
    private boolean isPhotoExist;
    private String photoUrl;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goodImg.setImage(new Image(PathHandler.withoutImageUrl));
        if(SellerProductHandlingController.sellerProductHandlingController.getCurrentGood() != null){
            good = SellerProductHandlingController.sellerProductHandlingController.getCurrentGood();
            nameField.setText(good.getName());
            brandField.setText(good.getBrand());
            priceField.setText(Integer.toString(good.getPrice()));
            stockField.setText(Integer.toString(good.getStock()));
            detailField.setText(good.getDetailInfo());
            categoryChoiceBox.getItems().addAll(ManageInfo.allCategories);
            errorMessage.setText("");
            if(good.hasImage()){
                goodImg.setImage(new Image(good.getImage()));
                isPhotoExist =true;
            }
        }
        else categoryChoiceBox.getItems().addAll(ManageInfo.allCategories);
    }

    public void confirm(ActionEvent actionEvent) {
        try {
            int priceText = Integer.parseInt(priceField.getText());
            int stockText = Integer.parseInt(stockField.getText());
            String nameText = nameField.getText();
            String brandText = brandField.getText();
            String detailText = detailField.getText();
            Category categoryValue = categoryChoiceBox.getValue();
            if(Good.isGoodWithName(nameText))
                Functions.showDialog("good with this name already exist",true);
            else if(categoryValue == null)
                Functions.showDialog("please choose a category",true);
            else if(brandText == "" || nameText=="" || detailText=="")
                Functions.showDialog("please set all fields",true);
            else {
                errorMessage.setStyle("-fx-background-color: #00ff00;");
                errorMessage.setText("Update Request has been sent");
                if (good == null) {
                    good = new Good(nameText, brandText, priceText, UserHandler.currentSeller, stockText, categoryValue, detailText, photoUrl);
                } else {
                    good.setName(nameText);
                    good.setBrand(brandText);
                    good.setPrice(priceText);
                    good.setStock(stockText);
                    good.setDetailInfo(detailText);
                    good.setCategory(categoryValue);
                    good.setImage(photoUrl);
                }
                Functions.showDialog("your request has been sent",false);
                SellerProductHandlingController.sellerProductHandlingController.initialize(null,null);
                MenuHandler.secondCurrentWindow.close();
            }
        }catch (Exception e) {
            errorMessage.setStyle("-fx-background-color: #ff0000;");
            errorMessage.setText("Invalid Change!");
        }
    }
    public void choosePhotoOnClick(ActionEvent actionEvent) {
        FileChooser fileChooser = Functions.prepareFileChooser();
        File selectedDir = fileChooser.showOpenDialog(MenuHandler.currentWindow);
        File imageFile = new File(selectedDir.getAbsolutePath());
        Image profileImage;
        String url = (String.valueOf(Functions.changePathToUrl(String.valueOf(imageFile))));
        profileImage = new Image(url);
        if(profileImage != null) {
            goodImg.setImage(profileImage);
            photoUrl = url;
            isPhotoExist = true;
        }
    }
    public void deletePhotoOnClick(ActionEvent actionEvent) {
        isPhotoExist = false;
        goodImg.setImage(new Image(PathHandler.withoutImageUrl));
    }
}
