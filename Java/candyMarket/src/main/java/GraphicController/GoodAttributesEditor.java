package GraphicController;

import GraphicView.CategoryPropertiesBox;
import GraphicView.MenuHandler;
import PathHandler.PathHandler;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GoodAttributesEditor implements Initializable {
    public ImageView goodImg;
    public ScrollPane propertiesScrollPane;
    private Good good;

    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;
    @FXML private TextField detailField;
    @FXML private ChoiceBox<String> categoryChoiceBox;

    @FXML private Label errorMessage;
    private boolean isPhotoExist;
    private String photoUrl;
    private ArrayList<CategoryPropertiesBox> properties;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Category c:
             ManageInfo.allCategories) {
            categoryChoiceBox.getItems().add(c.getName());
        }
        properties = new ArrayList<>();
        goodImg.setImage(new Image(PathHandler.withoutImageUrl));
        if(SellerProductHandlingController.sellerProductHandlingController.getCurrentGood() != null){
            good = SellerProductHandlingController.sellerProductHandlingController.getCurrentGood();
            nameField.setText(good.getName());
            nameField.setDisable(true);
            brandField.setText(good.getBrand());
            priceField.setText(Integer.toString(good.getPrice()));
            stockField.setText(Integer.toString(good.getStock()));
            detailField.setText(good.getDetailInfo());
            errorMessage.setText("");
            categoryChoiceBox.setValue(good.getCategory().getName());
            setProperties(good.getSpecialAttributes());
            if(good.hasImage()){
                goodImg.setImage(new Image(good.getImage()));
                isPhotoExist =true;
            }
        }
    }

    public void confirm(ActionEvent actionEvent) {
        try {
            int priceText = Integer.parseInt(priceField.getText());
            int stockText = Integer.parseInt(stockField.getText());
            String nameText = nameField.getText();
            String brandText = brandField.getText();
            String detailText = detailField.getText();
            Category categoryValue = Category.getCategoryByName(categoryChoiceBox.getValue());
            if(Good.isGoodWithName(nameText))
                Functions.showDialog("good with this name already exist",true);
            else if(categoryValue == null)
                Functions.showDialog("please choose a category",true);
            else if(brandText.equals("") || nameText.equals("") || detailText.equals(""))
                Functions.showDialog("please set all fields",true);
            else if(!isAllPropertiesSet())
                Functions.showDialog("please set all properties",true);
            else {
                errorMessage.setStyle("-fx-background-color: #00ff00;");
                errorMessage.setText("Update Request has been sent");
                if (good == null) {
                    good = new Good(nameText, brandText, priceText, UserHandler.currentSeller, stockText, categoryValue, detailText, photoUrl,getProperties());
                    new Request(Request.requestType.CREATE_GOOD).createAddProductRequest(good);
                } else {
                    new Request(Request.requestType.EDIT_GOOD).createEditProductRequest(new Good(good.getName(), brandText, priceText, good.getSeller(), stockText, categoryValue, detailText, photoUrl,getProperties()));
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
    private List<String> getProperties(){
        List<String> propertiesStrings = new ArrayList<>();
        for (CategoryPropertiesBox c:
             properties) {
            propertiesStrings.add(c.getPropertyValue().getText());
        }
        return propertiesStrings;
    }
    private void setProperties(List<String> propertiesString){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        ArrayList<CategoryPropertiesBox> categoryPropertiesBoxes = new ArrayList<>();
        Good good = SellerProductHandlingController.sellerProductHandlingController.getCurrentGood();
        Category category = good.getCategory();
        for (String s:
                propertiesString) {
            Label label = new Label(category.getSpecialAttributes().get(propertiesString.indexOf(s)));
            TextField textField = new TextField(s);
            CategoryPropertiesBox categoryPropertiesBox = new CategoryPropertiesBox(label,textField);
            categoryPropertiesBoxes.add(categoryPropertiesBox);
            vBox.getChildren().add(categoryPropertiesBox);
        }
        propertiesScrollPane.setContent(vBox);
        properties = categoryPropertiesBoxes;
    }
    private boolean isAllPropertiesSet(){
        List<String> propertiesStrings = new ArrayList<>();
        for (CategoryPropertiesBox c:
                properties) {
            if(c.getPropertyValue().getText().equals(""))
                return false;
        }
        return true;
    }
    public void categoryOnAction(ActionEvent actionEvent) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        for (String property:
                Category.getCategoryByName(categoryChoiceBox.getValue()).getSpecialAttributes()) {
            CategoryPropertiesBox categoryPropertiesBox = new CategoryPropertiesBox(new Label(property),new TextField());
            vBox.getChildren().add(categoryPropertiesBox);
            properties.add(categoryPropertiesBox);
        }
        propertiesScrollPane.setContent(vBox);
    }
}
