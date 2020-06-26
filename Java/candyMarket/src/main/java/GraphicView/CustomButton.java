package GraphicView;

import GraphicController.BorderPaneController;
import javafx.scene.control.Button;

import java.io.File;
import java.net.MalformedURLException;

public class CustomButton extends Button {


    public CustomButton(String buttonText,String gridPaneNameOpen) {
        try {
            this.getStylesheets().add(String.valueOf(new File(PathHandler.resourcePath+"CssFiles\\optionBarButtons.css").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.getStyleClass().add("myButton");
        this.setText(buttonText);
        this.gridPaneNameOpen = gridPaneNameOpen;
        this.setOnMouseClicked(actionEvent -> BorderPaneController.borderPaneController.setCenter(gridPaneNameOpen));
    }
    public CustomButton(String buttonText){
        try {
            this.getStylesheets().add(String.valueOf(new File(PathHandler.resourcePath+"CssFiles\\optionBarButtons.css").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.getStyleClass().add("myButton");
        this.setText(buttonText);
    }
    private String gridPaneNameOpen;
    public String getGridPaneNameOpen() {
        return gridPaneNameOpen;
    }
        public void setGridPaneNameOpen(String gridPaneNameOpen) {
        this.gridPaneNameOpen = gridPaneNameOpen;
    }
}
