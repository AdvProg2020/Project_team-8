package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PrimaryMenu;
import View.Menu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ManagerController extends PrimaryMenu {

    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button>  buttons = new ArrayList<>();
        buttons.add(MenuHandler.viewPersonalInfoBtn);
        buttons.add(MenuHandler.discountManagingBtn);
        buttons.add(MenuHandler.productManagingBtn);
        buttons.add(MenuHandler.backBtn);
        return null;
    }
}
