package GraphicController;

import GraphicView.MenuHandler;
import GraphicView.PrimaryMenu;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class SellerController extends PrimaryMenu {
    @Override
    public ArrayList<Button> getOptionBarButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(MenuHandler.viewProfileBtn);
        buttons.add(MenuHandler.viewSellerPersonalInfoBtn);
        buttons.add(MenuHandler.sellerProductManagingBtn);
        buttons.add(MenuHandler.sellerRequestsBtn);
        buttons.add(MenuHandler.backBtn);
        return buttons;
    }
}
