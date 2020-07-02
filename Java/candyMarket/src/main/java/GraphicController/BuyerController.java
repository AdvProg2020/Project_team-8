package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PrimaryMenu;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class BuyerController extends PrimaryMenu {
    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons= new ArrayList<>();
        buttons.add(MenuHandler.viewProfileBtn);
        buttons.add(MenuHandler.viewBuyerPersonalInfoBtn);
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }
}
