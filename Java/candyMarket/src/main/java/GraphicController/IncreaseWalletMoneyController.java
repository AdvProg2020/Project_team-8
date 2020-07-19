package GraphicController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class IncreaseWalletMoneyController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField accNumField;
    @FXML private TextField moneyField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void paying(ActionEvent actionEvent) {
        int accountNum;
        int moneyAmount;
        boolean going = true;
        try {
            accountNum = Integer.parseInt(accNumField.getText());
        } catch (NumberFormatException e) {
            Functions.showDialog("Enter A valid account number", true);
            going = false;
        }
        if (going) {
            try {
                moneyAmount = Integer.parseInt(moneyField.getText());
            } catch (NumberFormatException e) {
                Functions.showDialog("Enter A valid amount of money", true);
                going = false;
            }
            if (going) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                //...
            }
        }
    }
}
