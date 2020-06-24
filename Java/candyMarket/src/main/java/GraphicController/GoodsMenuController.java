package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PrimaryMenu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GoodsMenuController extends PrimaryMenu {

    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }

}
