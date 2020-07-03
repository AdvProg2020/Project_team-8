package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PrimaryMenu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ManagerController extends PrimaryMenu {

    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(MenuHandler.viewProfileBtn);
        buttons.add(MenuHandler.clientManagingBtn);
        buttons.add(MenuHandler.discountManagingBtn);
        buttons.add(MenuHandler.productManagingBtn);
        buttons.add(MenuHandler.categoryManagingBtn);
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }
}
