import GraphicView.MenuHandler;
import View.ConsoleDesign;
import View.MainMenu;
import View.Menu;
import View.ViewException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuHandler.currentWindow = primaryStage;
        MenuHandler.changeScene("MainMenu");
    }
}
