package GraphicView;

import GraphicController.BorderPaneController;
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
        stage.setScene(new Scene(root, 450, 450));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public static Button viewPersonalInfoBtn;
    public static Button backBtn;
    public static Button exitBtn;
    public static Button goodsMenuBtn;
    public static Button clientMenuBtn;
    public static Button viewGoods;
    public static void createButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        viewPersonalInfoBtn = new CustomButton("ViewPersonalInfo","PersonalInfo");
        backBtn =new CustomButton("Back");
        backBtn.setOnMouseClicked(actionEvent -> {
            BorderPaneController.borderPaneController.setCenter("MainMenu");
        });
        clientMenuBtn = new CustomButton("ClientMenu");
        clientMenuBtn.setOnAction(actionEvent -> {
            BorderPaneController.borderPaneController.setCenter("MainMenu");

        });
        exitBtn =new CustomButton("Exit");
        exitBtn.setOnAction(actionEvent -> Platform.exit());
        buttons.clear();
        buttons.add(backBtn);
        viewGoods = new CustomButton("ViewGoods","ViewGoods");
        goodsMenuBtn = new CustomButton("GoodsMenu","GoodsMenu");
    }
}
