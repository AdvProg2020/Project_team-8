package GraphicController;

import GraphicView.CustomBorderPaneMenus;
import GraphicView.MenuHandler;
import Model.User;
import Model.UserHandler;
import View.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginMenuController extends CustomBorderPaneMenus implements Initializable {

    @FXML private TextField username;
    @FXML private PasswordField password;

    @FXML private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessage.setText("");
    }

    public void LoggingIn(ActionEvent actionEvent) {
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (!User.isThereUserWithUsername(usernameText)) {
            errorMessage.setText("Username does not exist");
        }
        else if (!(User.getUserByUsername(usernameText).getPassword().equals(passwordText))) {
            errorMessage.setText("Wrong Password!");
        }
        else {
            BorderPaneController.borderPaneController.login(usernameText);
            MenuHandler.secondCurrentWindow.close();
        }
    }
}
