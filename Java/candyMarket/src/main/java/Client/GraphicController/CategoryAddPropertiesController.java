package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import Client.Model.Category;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryAddPropertiesController implements Initializable {
    public TextField propertiesNameTxt;
    public Button addPropertyBtn;
    public Button confirmBtn;
    public ScrollPane propertiesScrollPane;
    private List<String> properties;
    public static CategoryAddPropertiesController categoryAddPropertiesController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
              properties = new ArrayList<>();
              if(CategoryManagingController.categoryManagingController.isEditMode()){
                  properties = CategoryManagingController.categoryManagingController.getTableView().getSelectionModel().getSelectedItem().getSpecialAttributes();
                  propertyListUpdate();
              }
    }
    public void addPropertyBtnOnClick(MouseEvent mouseEvent) {
           if(propertiesNameTxt.getText().equals(""))
               Functions.showDialog("please enter property name",true);
           else {
               properties.add(propertiesNameTxt.getText());
               propertyListUpdate();
           }
    }
    public List<String> getProperties(){
        return properties;
    }
    public void setProperties(List<String> properties){
        this.properties = properties;
    }
    private void propertyListUpdate(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        for (String s:
             properties) {
            Label label = new Label(s);
            vBox.getChildren().add(label);
        }
        propertiesScrollPane.setContent(vBox);
    }
    public void confirmBtnOnClick(MouseEvent mouseEvent) {
        if(CategoryManagingController.categoryManagingController.isEditMode()){
            Functions.showDialog(" category edited successfully", false);
            CategoryManagingController.categoryManagingController.getTableView().getSelectionModel().getSelectedItem()
                    .setSpecialAttributes(properties);
        }else {
            Functions.showDialog(" category added successfully", false);
            new Category(CategoryManagingController.categoryManagingController.getCategoryName(), properties);
        }
        MenuHandler.secondCurrentWindow.close();
        CategoryManagingController.categoryManagingController.initialize(null, null);
    }
}
