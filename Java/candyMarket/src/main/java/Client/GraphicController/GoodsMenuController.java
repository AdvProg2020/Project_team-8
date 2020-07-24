package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import Client.GraphicView.PrimaryMenu;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class GoodsMenuController extends PrimaryMenu {


    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(MenuHandler.backBtn);
        buttons.add(MenuHandler.viewGoods);
        return buttons;
    }

}
