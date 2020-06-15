package GraphicView;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MenuHandler {
    public static Stage currentWindow;
    public static Scene currentScene;
    public static void changeScene(String fxml) {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream inputStream = new FileInputStream("C:/Users/Asus/Documents/GitHub/ApProject/Java/candyMarket/src/main/java/GraphicView/" +
                    fxml+".fxml");
            root = fxmlLoader.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentWindow.setMinHeight(500);
        currentWindow.setMinWidth(1000);
        currentWindow.setMaxHeight(500);
        currentWindow.setMaxWidth(1000);
        currentWindow.setTitle("Menu");
        currentScene = new Scene(root, 300, 200);
        currentScene.getRoot().requestFocus();
        ((BorderPane)currentScene.getRoot()).setTop(getTopHBox());
        ((BorderPane)currentScene.getRoot()).setLeft(getLeftBox());
        ((BorderPane)currentScene.getRoot()).setBottom(getButtomBox());
        ((BorderPane)currentScene.getRoot()).setRight(getRightBox());
        currentWindow.setScene(currentScene);
        currentWindow.setResizable(false);
        currentWindow.centerOnScreen();
        currentWindow.show();
    }
    public static HBox getTopHBox() {
        javafx.scene.control.Menu fileMenu = new Menu( "File");
        javafx.scene.control.Menu editMenu = new Menu( "Edit");

        // create menuitems
        MenuItem fileMenu1 = new MenuItem("Restart");
        MenuItem fileMenu2 = new MenuItem("Close");
        MenuItem editMenu1 = new MenuItem("Size");
        // add menu items to menu
        fileMenu.getItems().addAll(fileMenu1,fileMenu2);
        editMenu.getItems().addAll(editMenu1);
        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().addAll(fileMenu,editMenu);
        HBox hbox = new HBox(mb);
        hbox.setPadding(new Insets(10, 8, 10, 8));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: grey;");
        return hbox;
    }
    public static VBox getLeftBox() {
        Button backBtn = new Button("Back");
        backBtn.setPrefHeight(1000);
        VBox vBox = new VBox(backBtn);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: dimgray;");
        return vBox;
    }
    public static VBox getRightBox() {
        VBox VBox = new VBox();
        VBox.setPadding(new Insets(15, 12, 15, 12));
        VBox.setSpacing(10);
        VBox.setStyle("-fx-background-color: yellowgreen;");
        return VBox;
    }
    public static HBox getButtomBox() {
        HBox HBox = new HBox();
        HBox.setPadding(new Insets(15, 12, 15, 12));
        HBox.setSpacing(10);
        HBox.setStyle("-fx-background-color: pink;");
        return HBox;
    }
}
