import View.ConsoleDesign;
import View.MainMenu;
import View.ViewException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GraphicView/FirstManagerLogin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("GraphicView/MainMenu.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("CandyShop!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
