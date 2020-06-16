package GraphicController;

import GraphicView.CustomButton;
import GraphicView.MenuHandler;
import View.Menu;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BorderPaneController {

    public VBox optionBar;

    public static void setOptionBar(ArrayList<Button> buttons) {
        /*for (CustomButton b:
             buttons) {
            optionBar.getChildren().add(b);
        }
        ???*/
        if(MenuHandler.currentMenuOptionBarButtons != null)MenuHandler.currentParentMenuOptionBarButtons = MenuHandler.currentMenuOptionBarButtons;
        MenuHandler.currentMenuOptionBarButtons = buttons;
        Node node = ((BorderPane)MenuHandler.currentWindow.getScene().getRoot()).getLeft();
        ((VBox)node).getChildren().clear();
        for (Button b:
             buttons) {
            ((VBox)node).getChildren().add(b);
        }
        ((BorderPane)MenuHandler.currentWindow.getScene().getRoot()).setLeft((VBox)node);
    }
}
