package Client.GraphicController;

import Client.Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SellerBalanceHandlingController implements Initializable {
    @FXML private Label currentBalanceLabel;

    @FXML public TextField usernameField;
    @FXML public TextField moneyField;
    @FXML public TextField accNumField;
    @FXML public PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentBalanceLabel.setText(String.valueOf(UserHandler.currentSeller.getBalance()));
    }

    public void withdrawal(ActionEvent actionEvent) {
        //bank stuff
    }
}
