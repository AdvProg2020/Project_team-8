package GraphicView;

import GraphicController.BorderPaneController;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class CustomButton extends Button {


    public CustomButton(String buttonText,String gridPaneNameOpen, ArrayList<Button> optionBarButtons) {
        this.setText(buttonText);
        this.gridPaneNameOpen = gridPaneNameOpen;
        this.optionBarButtons = optionBarButtons;
        this.setOnMouseClicked(actionEvent -> MenuHandler.changeScene(gridPaneNameOpen));
    }
    private String gridPaneNameOpen;
    ArrayList<Button> optionBarButtons;
    public String getGridPaneNameOpen() {
        return gridPaneNameOpen;
    }
        public void setGridPaneNameOpen(String gridPaneNameOpen) {
        this.gridPaneNameOpen = gridPaneNameOpen;
    }
}
