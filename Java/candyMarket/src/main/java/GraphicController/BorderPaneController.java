package GraphicController;

import GraphicView.CustomButton;
import GraphicView.MenuHandler;
import View.Menu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BorderPaneController {

    public VBox optionBar;
    public Button loginRegisterButton;

    public static void setOptionBar(ArrayList<Button> buttons) {
        /*for (CustomButton b:
             buttons) {
            optionBar.getChildren().add(b);
        }
        ???*/
        Node node = ((BorderPane)MenuHandler.currentWindow.getScene().getRoot()).getLeft();
        ((VBox)node).getChildren().clear();
        for (Button b:
             buttons) {
            ((VBox)node).getChildren().add(b);
        }
        ((BorderPane)MenuHandler.currentWindow.getScene().getRoot()).setLeft((VBox)node);
    }
    public void loginRegisterBtnOnClick(MouseEvent mouseEvent) {
        MenuHandler.createStageWithScene("RegisterMenu");
    }
}
