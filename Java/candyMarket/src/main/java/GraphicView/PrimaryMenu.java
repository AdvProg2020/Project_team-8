package GraphicView;

import GraphicController.BorderPaneController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class PrimaryMenu implements Initializable {
    ArrayList<Button> optionBarButtons;
    public abstract ArrayList<Button> getOptionBarButtons();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BorderPaneController.setLeft(getOptionBarButtons());
    }
}
