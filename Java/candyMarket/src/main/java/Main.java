import GraphicController.BorderPaneController;
import GraphicView.MenuHandler;
import GraphicView.PathHandler;
import Model.Category;
import Model.Good;
import Model.Manager;
import Model.UserHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\GraphicView\\";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuHandler.createButtons();
        MenuHandler.currentWindow = primaryStage;
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream inputStream = new FileInputStream(PathHandler.fxmlPath + "BorderPane" + ".fxml");
            root = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuHandler.currentWindow.setMinHeight(600);
        MenuHandler.currentWindow.setMinWidth(1200);
        MenuHandler.currentWindow.setMaxHeight(600);
        MenuHandler.currentWindow.setMaxWidth(1200);
        MenuHandler.currentWindow.setTitle("Menu");
        MenuHandler.currentScene = new Scene(root, 300, 200);
        MenuHandler.currentScene.getRoot().requestFocus();
        MenuHandler.currentWindow.setScene(MenuHandler.currentScene);
        MenuHandler.currentWindow.setResizable(false);
        MenuHandler.currentWindow.centerOnScreen();
        MenuHandler.currentWindow.show();
        debug();
        if(Manager.isThisTheFirstManager()) MenuHandler.createStageWithScene("FirstManagerLogin");
        //else
        BorderPaneController.borderPaneController.setCenter("MainMenu");


    }
    private static void debug(){
        new Category("people",null);
        new Category("food",null);
        new Category("fruits",null);
        new Good("Mz","MzBrand",100,null,1,Category.getCategoryByName("people"),"","");
        new Good("Arash","ArashBrand",100,null,1,Category.getCategoryByName("people"),"","");
        new Good("Reza","RezaBrand",200,null,1,Category.getCategoryByName("people"),"","");
        new Good("Reza2","RezaBrand",200,null,1,Category.getCategoryByName("people"),"","");
        new Good("Pizza","FastFood420",1000,null,4,Category.getCategoryByName("food"),"","");
        new Manager("admin", "kin", "gro", "k@gmail.com", "+98142", "admin");
        BorderPaneController.borderPaneController.login("admin");
    }
}
