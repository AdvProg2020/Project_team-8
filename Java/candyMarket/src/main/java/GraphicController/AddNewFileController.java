package GraphicController;

import GraphicView.CategoryPropertiesBox;
import GraphicView.MenuHandler;
import Model.*;
import PathHandler.PathHandler;
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
import java.util.ResourceBundle;

public class AddNewFileController implements Initializable {
    public ImageView goodImg;
    private String photoUrl;
    private boolean isPhotoExist;

    private File selectedFile;

    private Good good;

    @FXML private Label errorMessage;
    @FXML private Label fileLabel;

    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;
    @FXML private TextField detailField;
    @FXML private ChoiceBox<String> categoryChoiceBox;
    private ArrayList<CategoryPropertiesBox> properties;
    @FXML public ScrollPane propertiesScrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Category c:
                ManageInfo.allCategories) {
            categoryChoiceBox.getItems().add(c.getName());
        }
        selectedFile = null;
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
            setProperties((ArrayList<String>) good.getSpecialAttributes());
            if(good.hasImage()){
                goodImg.setImage(new Image(good.getImage()));
                isPhotoExist =true;
            }
        }
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
            else if (selectedFile == null) {
                Functions.showDialog("Select a File!!", true);
            }
            else {
                errorMessage.setStyle("-fx-background-color: #00ff00;");
                errorMessage.setText("Update Request has been sent");
                FileGood filegood = new FileGood(nameText, brandText, priceText, UserHandler.currentSeller,
                        stockText, categoryValue, detailText, photoUrl,getProperties(), selectedFile);
                ManageInfo.allFileGoods.add(filegood);
                ManageInfo.allGoods.add(filegood);
                Functions.showDialog("your request has been sent",false);
                SellerProductHandlingController.sellerProductHandlingController.initialize(null,null);
                MenuHandler.secondCurrentWindow.close();
            }
        }catch (Exception e) {
            Functions.showDialog("Invalid changes", true);
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

    private ArrayList<String> getProperties(){
        ArrayList<String> propertiesStrings = new ArrayList<>();
        for (CategoryPropertiesBox c:
                properties) {
            propertiesStrings.add(c.getPropertyValue().getText());
        }
        return propertiesStrings;
    }

    private void setProperties(ArrayList<String> propertiesString){
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
        ArrayList<String> propertiesStrings = new ArrayList<>();
        for (CategoryPropertiesBox c:
                properties) {
            if(c.getPropertyValue().getText().equals(""))
                return false;
        }
        return true;
    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            fileLabel.setText("File selected: " + selectedFile.getName());
        }
        else {
            fileLabel.setText("File selection cancelled.");
        }
    }
}
