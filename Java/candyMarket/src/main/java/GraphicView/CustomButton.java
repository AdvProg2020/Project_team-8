package GraphicView;

import GraphicController.BorderPaneController;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class CustomButton extends Button {


    public CustomButton(String buttonText,String gridPaneNameOpen, ArrayList<Button> optionBarButtons,boolean changeMenu) {
        this.setText(buttonText);
        this.gridPaneNameOpen = gridPaneNameOpen;
        this.optionBarButtons = optionBarButtons;
        /*if(type==buttonType.back) {
            this.setOnMouseClicked(actionEvent -> {
                MenuHandler.changeScene("MainMenu");
                BorderPaneController.setOptionBar(MenuHandler.currentParentMenuOptionBarButtons);
            });
        }
        else if(type==buttonType.exit)
            this.setOnMouseClicked(actionEvent -> Platform.exit());*/
        if(changeMenu){
            this.setOnMouseClicked(actionEvent -> {
                BorderPaneController.setOptionBar(optionBarButtons);
                MenuHandler.currentParentMenuName= MenuHandler.currentMenuName;
                MenuHandler.changeScene(gridPaneNameOpen);
            } );
        }
        else this.setOnMouseClicked(actionEvent -> MenuHandler.changeScene(gridPaneNameOpen));
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
