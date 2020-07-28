package Client.GraphicController;

import Client.DataHandler.MessageHandler;
import Client.DataHandler.WalletExceptions;
import Client.Model.ManageInfo;
import Client.Model.User;
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
        if(UserHandler.currentSeller.getBalance()-Integer.valueOf(moneyField.getText())< ManageInfo.allManagers.get(0
        ).getMinWalletMoney())
            Functions.showDialog("you should have at least "+ManageInfo.allManagers.get(0).getMinWalletMoney()+" money " +
                    "in your wallet",true);
        else
        try {
            MessageHandler.sendMoneyWithdrawMessage(usernameField.getText()
            ,passwordField.getText(),moneyField.getText(),accNumField.getText());
            UserHandler.currentSeller.setBalance(UserHandler.currentSeller.getBalance()-Integer.valueOf(moneyField.getText()));
        } catch (WalletExceptions walletExceptions) {
            Functions.showDialog(walletExceptions.getMessage(),true);
        }
    }
}
