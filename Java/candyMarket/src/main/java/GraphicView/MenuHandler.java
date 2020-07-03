package GraphicView;

import GraphicController.BorderPaneController;
import Model.UserHandler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EventListener;

public class MenuHandler {
    public static Stage currentWindow;
    public static Scene currentScene;
    public static VBox currentOptionBar;
    public static String currentParentMenuName;
    public static String currentMenuName;
    public static Stage secondCurrentWindow;

    public static Pane getPaneByName(String fxml){
        Pane root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream inputStream = new FileInputStream(PathHandler.fxmlPath + fxml + ".fxml");
            root = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
    public static void createStageWithScene(String fxml){
        Pane root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream inputStream = new FileInputStream(PathHandler.fxmlPath + fxml + ".fxml");
            root = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        secondCurrentWindow = stage;
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public static Button backBtn;
    public static Button exitBtn;
    public static Button goodsMenuBtn;
    public static Button clientMenuBtn;
    public static Button viewGoods;
    public static Button viewProfileBtn;
    public static Button clientManagingBtn;
    public static Button productManagingBtn;
    public static Button discountManagingBtn;
    public static Button categoryManagingBtn;
    public static Button viewBuyerPersonalInfoBtn;
    public static Button viewSellerPersonalInfoBtn;
    public static Button sellerProductManagingBtn;
    public static Button requestsBtn;

    public static void createButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        viewProfileBtn = new CustomButton("ViewProfile","Profile");
        backBtn =new CustomButton("Back");
        backBtn.setOnMouseClicked(actionEvent -> {
            BorderPaneController.borderPaneController.setCenter("MainMenu");
        });
        clientMenuBtn = new CustomButton("ClientMenu");
        clientMenuBtn.setOnAction(actionEvent -> {
            clientMenuBtnOnClick();

        });
        exitBtn =new CustomButton("SaveAndExit");
        exitBtn.setOnAction(actionEvent -> Platform.exit());
        buttons.clear();
        buttons.add(backBtn);
        viewGoods = new CustomButton("ViewGoods","ViewGoods");
        goodsMenuBtn = new CustomButton("GoodsMenu","GoodsMenu");
        clientManagingBtn = new CustomButton("ClientManaging","ClientsProfileForManager");
        productManagingBtn = new CustomButton("ProductsManaging","ProductsManaging");
        discountManagingBtn = new CustomButton("DiscountManaging","DiscountCodeManaging");
        discountManagingBtn = new CustomButton("DiscountManaging","DiscountCodeManaging");
        categoryManagingBtn = new CustomButton("CategoryManaging","CategoryManaging");
        viewBuyerPersonalInfoBtn = new CustomButton("ViewPersonalInfo","BuyerPersonalInfo");
        viewSellerPersonalInfoBtn = new CustomButton("ViewPersonalInfo","SellerPersonalInfo");
        sellerProductManagingBtn = new CustomButton("ProductManaging","SellerProductHandling");
        requestsBtn = new CustomButton("Requests", "ManagerRequestMenu");
    }
    public static void clientMenuBtnOnClick(){
        if(UserHandler.isLogeIn())
        {
            if(UserHandler.getCurrentUser().isManager())
                BorderPaneController.borderPaneController.setCenter("ManagerMenu");
            else if(UserHandler.getCurrentUser().isSeller())
                BorderPaneController.borderPaneController.setCenter("SellerMenu");
            else if(UserHandler.getCurrentUser().isBuyer())
                BorderPaneController.borderPaneController.setCenter("BuyerMenu");
        }
        else BorderPaneController.borderPaneController.loginBtnClick(null);
    }
}
