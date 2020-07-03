package GraphicController;

import GraphicView.CustomBorderPaneMenus;
import GraphicView.MenuHandler;
import GraphicView.PathHandler;
import Model.UserHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class BorderPaneController extends CustomBorderPaneMenus implements Initializable {
    public VBox optionBar;
    public String currentUserName;
    public static  BorderPaneController borderPaneController;
    public Button logOutBtn;
    public Button logInBtn;
    public Button registerBtn;
    public VBox userLogOutBar;
    public VBox userLoginBar;
    public BorderPane borderPane;
    public ImageView cartImage;
    public ImageView profileImage;
    public static void setLeft(ArrayList<Button> buttons) {
        Node node = ((BorderPane)MenuHandler.currentWindow.getScene().getRoot()).getLeft();
        ((VBox)node).getChildren().clear();
        for (Button b:
             buttons) {
            ((VBox)node).getChildren().add(b);
        }
        ((BorderPane)MenuHandler.currentWindow.getScene().getRoot()).setLeft((VBox)node);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPaneController = this;
        try {
            borderPane.getStylesheets().add(String.valueOf(new File(PathHandler.resourcePath+"\\CssFiles\\BorderPane.css").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = null;
        Image imageCart = null;
        Image imageProfile = null;
        try {
            imageCart = new Image(String.valueOf(new File(PathHandler.resourcePath+"Photos\\Shopping-Cart-PNG.png").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        cartImage.setImage(imageCart);
        try {
            imageProfile = new Image(String.valueOf(new File(PathHandler.resourcePath+"Photos\\profile-buttons-png-3.png").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        profileImage.setImage(imageProfile);
        try {
            image = new Image(String.valueOf(new File(PathHandler.resourcePath+"Photos\\MainBackGround.jpg").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BackgroundImage backgroundImage = new BackgroundImage(image,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        centerPane.setBackground(background);
        userLoginBar.toFront();
        userLogOutBar.setVisible(false);
    }

    public void logOutBtnOnClick(MouseEvent mouseEvent) {
        UserHandler.loggingOut();
        userLoginBar.toFront();
        userLoginBar.setVisible(true);
        userLogOutBar.setVisible(false);
        setCenter("MainMenu");
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);
        mediaPlayer.play();
    }
    public void registerBtnOnClick(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);
        mediaPlayer.play();
        MenuHandler.createStageWithScene("RegisterMenu");
    }
    public void login(String currentUserName){
        UserHandler.loggingIn(currentUserName);
        Functions.showDialog("successfully logged in",false);
        userLogOutBar.toFront();
        userLoginBar.setVisible(false);
        userLogOutBar.setVisible(true);
    }
    public void loginBtnClick(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);
        mediaPlayer.play();
        MenuHandler.createStageWithScene("LoginMenu");
    }


    public void Buying(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);
        mediaPlayer.play();
        MenuHandler.secondCurrentWindow.close();
        MenuHandler.createStageWithScene("Cart");
    }

    public void viewProfile(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);
        mediaPlayer.play();
        if (UserHandler.isLogeIn()) {
            this.setCenter("Profile");
            MenuHandler.clientMenuBtnOnClick();
        }
        else {
            MenuHandler.createStageWithScene("LoginMenu");
        }
    }
}
