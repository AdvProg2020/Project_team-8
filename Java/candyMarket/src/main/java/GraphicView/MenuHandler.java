package GraphicView;

import GraphicController.BorderPaneController;
import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
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
    public static ArrayList<Button> currentParentMenuOptionBarButtons;
    public static ArrayList<Button> currentMenuOptionBarButtons;
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\GraphicView\\";
    public static String fxmlPath2 = "C:/Users/Asus/Documents/GitHub/ApProject/Java/candyMarket/src/main/java/GraphicView/";
    public static void changeScene(String fxml) {
        Pane root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream inputStream = new FileInputStream(fxmlPath + fxml + ".fxml");
            root = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentScene = new Scene(root, 300, 200);
        currentScene.getRoot().requestFocus();

        ((BorderPane)currentWindow.getScene().getRoot()).setCenter(((GridPane)currentScene.getRoot()));
    }
    public static Button viewPersonalInfoBtn;
    public static Button backBtn;
    public static Button exitBtn;
    public static Button goodsMenuBtn;
    public static void createButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        viewPersonalInfoBtn = new CustomButton("ViewPersonalInfo","PersonlInfo",buttons,false);
        backBtn =new Button("Back");
        backBtn.setOnMouseClicked(actionEvent -> {
            MenuHandler.changeScene("MainMenu");
            BorderPaneController.setOptionBar(MenuHandler.currentParentMenuOptionBarButtons);
        });
        exitBtn =new Button("Exit");
        exitBtn.setOnAction(actionEvent -> Platform.exit());
        buttons.clear();
        buttons.add(backBtn);
        goodsMenuBtn = new CustomButton("GoodsMenu","GoodsMenu",buttons, true);
    }
}
