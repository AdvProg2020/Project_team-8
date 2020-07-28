package Client;

import Client.GraphicController.BorderPaneController;
import Client.GraphicView.MenuHandler;
import Client.Model.*;
import BothUtl.PathHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\Client.Client.GraphicView\\";
    public static void main(String[] args) throws IOException {
        Controller.connectToServer();
        launch(args);
        if(UserHandler.getCurrentUser()!=null) {
            UserHandler.getCurrentUser().setOnline(false);
            Controller.saveOrUpdateObject(UserHandler.getCurrentUser());
        }
        Controller.disconnectFromServer();
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
        MenuHandler.currentWindow.setMinHeight(650);
        MenuHandler.currentWindow.setMinWidth(1200);
        MenuHandler.currentWindow.setMaxHeight(650);
        MenuHandler.currentWindow.setMaxWidth(1200);
        MenuHandler.currentWindow.setTitle("Menu");
        MenuHandler.currentScene = new Scene(root, 300, 200);
        MenuHandler.currentScene.getRoot().requestFocus();
        MenuHandler.currentWindow.setScene(MenuHandler.currentScene);
        MenuHandler.currentWindow.setResizable(false);
        MenuHandler.currentWindow.centerOnScreen();
        MenuHandler.currentWindow.show();
        BorderPaneController.borderPaneController.setCenter("MainMenu");
        //addSellerAndStuff();
        //addItemsFirstStart();
        //debug();
        //test();
        if(Manager.isThisTheFirstManager()) MenuHandler.createStageWithScene("FirstManagerLogin");
        //else
    }
    private static void test(){
        System.out.println(ManageInfo.allGoods.get(0).getCategory());
    }
    private static void addSellerAndStuff(){
        Seller seller = new Seller("a","a","a","a@a.com","31231","a","a");
        ManageInfo.allSellers.add(seller);
        ManageInfo.allUsers.add(seller);
        List<String> attributes = new ArrayList<>();
        attributes.add("weight");
        Category category = new Category("food",attributes);
        attributes.clear();
        attributes.add("50");
        Good good = new Good("a","a",2,seller,2,category,"dsadsasad",null,null,attributes);
        ManageInfo.allGoods.add(good);
        seller.addGood(good);
        //System.out.println(ManageInfo.allGoods.get(0).getCategory());
    }
    private static void addItemsFirstStart(){
        new Category("people",null);
        new Category("food",null);
        new Category("fruits",null);
        //
        Seller seller = new Seller("a","a","a","a@a.com","31231","a","a");
        ManageInfo.allSellers.add(seller);
        ManageInfo.allUsers.add(seller);
        new Good("Mz","MzBrand",100,seller,1,Category.getCategoryByName("people"),"",null,null,null);
        new Good("Arash","ArashBrand",100,seller,1,Category.getCategoryByName("people"),"",null,null,null);
        new Good("Reza","RezaBrand",200,seller,1,Category.getCategoryByName("people"),"",null,null,null);
        new Good("Reza2","RezaBrand",200,seller,1,Category.getCategoryByName("people"),"",null,null,null);
        new Good("Pizza","FastFood420",1000,seller,4,Category.getCategoryByName("food"),"",null,null,null);
        BorderPaneController.borderPaneController.login("admin");
    }
}
