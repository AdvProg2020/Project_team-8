package Client.GraphicController;

import Client.GraphicView.*;
import Client.GraphicView.MenuHandler;
import BothUtl.PathHandler;
import Client.Model.*;
import javafx.animation.Animation;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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
    public ImageView chatImage;
    public ImageView spriteImageView;
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
        Image imageChat = null;
        Image spriteImage = null;
        try {
            spriteImage = new Image(String.valueOf(new File(PathHandler.resourcePath + "Photos\\SpriteAnimation.jpg").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        spriteImageView.setImage(spriteImage);
        spriteImageView.setViewport(new Rectangle2D(0, 0, 100, 100));
        Animation animation = new SpriteAnimation(spriteImageView, Duration.millis(2000),7, 180, 0, 0, 170, 400);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
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
            imageChat = new Image(String.valueOf(new File(PathHandler.resourcePath+"Photos\\chatIcon.png").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        chatImage.setImage(imageChat);
        chatImage.setVisible(false);
        chatImage.setDisable(true);
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
        chatImage.setVisible(false);
        chatImage.setDisable(true);
        setCenter("MainMenu");
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);

    }
    public void registerBtnOnClick(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);

        MenuHandler.createStageWithScene("RegisterMenu");
    }
    public void login(String currentUserName){
        UserHandler.loggingIn(currentUserName);
        Functions.showDialog("successfully logged in",false);
        userLogOutBar.toFront();
        userLoginBar.setVisible(false);
        userLogOutBar.setVisible(true);
        if (UserHandler.isSupporter() || UserHandler.isBuyer()) {
            chatImage.setVisible(true);
            chatImage.setDisable(false);
        }
    }
    public void loginBtnClick(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);

        MenuHandler.createStageWithScene("LoginMenu");
    }


    public void Buying(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);

        MenuHandler.secondCurrentWindow.close();
        MenuHandler.createStageWithScene("Cart");
    }

    public void viewProfile(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);

        if (UserHandler.isLogeIn()) {
            this.setCenter("Profile");
            MenuHandler.clientMenuBtnOnClick();
        }
        else {
            MenuHandler.createStageWithScene("LoginMenu");
        }
    }

    public void chatting(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = new MediaPlayer(PathHandler.buttonClickMedia);

        MenuHandler.createStageWithScene("ChatPage");
    }
}
