package Client.GraphicController;

import Client.Controller;
import Client.DataHandler.MessageHandler;
import Client.DataHandler.WalletExceptions;
import Client.Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IncreaseWalletMoneyController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField accNumField;
    @FXML private TextField moneyField;
    @FXML private Label balanceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        balanceLabel.setText(Integer.toString(UserHandler.currentBuyer.getBalance()));
    }

    private void addMoney(boolean withDraw){
        int accountNum = 0;
        int moneyAmount = 0;
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
                try {
                    if(withDraw)
                    MessageHandler.sendMoneyWithdrawMessage(username,password,String.valueOf(moneyAmount),String.valueOf(accountNum));
                    else MessageHandler.sendMoneyDepositMessage(username,password,String.valueOf(moneyAmount),String.valueOf(accountNum));
                    if(withDraw) {
                        UserHandler.currentBuyer.setBalance(UserHandler.currentBuyer.getBalance() + moneyAmount);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Controller.saveOrUpdateObject(UserHandler.currentBuyer);
                        Functions.showDialog("successfully wallet increased", false);
                        initialize(null,null);
                    }
                    else {
                        Functions.showDialog("successfully account money increased", false);
                        initialize(null,null);
                    }
                } catch (WalletExceptions walletExceptions) {
                    Functions.showDialog(walletExceptions.getMessage(),true);
                }
            }
        }
    }
    public void withdraw(ActionEvent actionEvent) {
        addMoney(true);
    }

    public void deposit(ActionEvent actionEvent) {
        addMoney(false);
    }
}
