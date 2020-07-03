import GraphicController.BorderPaneController;
import GraphicView.MenuHandler;
import GraphicView.PathHandler;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\GraphicView\\";
    public static void main(String[] args) throws IOException {
        FileHandler.getDataFromFiles();
        launch(args);
        FileHandler.setDataIntoFiles();
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
        MenuHandler.currentWindow.setMinHeight(700);
        MenuHandler.currentWindow.setMinWidth(1200);
        MenuHandler.currentWindow.setMaxHeight(700);
        MenuHandler.currentWindow.setMaxWidth(1200);
        MenuHandler.currentWindow.setTitle("Menu");
        MenuHandler.currentScene = new Scene(root, 300, 200);
        MenuHandler.currentScene.getRoot().requestFocus();
        MenuHandler.currentWindow.setScene(MenuHandler.currentScene);
        MenuHandler.currentWindow.setResizable(false);
        MenuHandler.currentWindow.centerOnScreen();
        MenuHandler.currentWindow.show();
        BorderPaneController.borderPaneController.setCenter("MainMenu");
        debug();
        //test();
        if(Manager.isThisTheFirstManager()) MenuHandler.createStageWithScene("FirstManagerLogin");
        //else
    }
    private static void test(){

    }
    private static void debug(){
        new Category("people",null);
        new Category("food",null);
        new Category("fruits",null);
        //
        Seller seller = new Seller("a","a","a","a@a.com","31231","a","a");
        new Good("Mz","MzBrand",100,seller,1,Category.getCategoryByName("people"),"",null);
        new Good("Arash","ArashBrand",100,seller,1,Category.getCategoryByName("people"),"",null);
        new Good("Reza","RezaBrand",200,seller,1,Category.getCategoryByName("people"),"",null);
        new Good("Reza2","RezaBrand",200,seller,1,Category.getCategoryByName("people"),"",null);
        Cart.addGood(new Good("Pizza","FastFood420",1000,seller,4,Category.getCategoryByName("food"),"",null));
        new Manager("admin", "kin", "gro", "k@gmail.com", "+98142", "admin");
        //BorderPaneController.borderPaneController.login("admin");
    }
}
