package Client.GraphicController;

import Client.DataHandler.MessageHandler;
import Client.DataHandler.WalletExceptions;
import Client.Model.Cart;
import Client.Model.ManageInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PayWithBankAccountController implements Initializable {

    @FXML public TextField usernameField;
    @FXML public TextField accNumField;
    @FXML public PasswordField passwordField;

    @FXML public Label balanceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        balanceLabel.setText(Integer.toString(Cart.getTotalAmount()));
    }

    public void paying(ActionEvent actionEvent) {
        try {
            MessageHandler.sendMoneyWithdrawMessage(usernameField.getText()
                    ,passwordField.getText(), String.valueOf(Cart.getTotalAmount()),accNumField.getText());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MessageHandler.sendMoneyDepositMessage(usernameField.getText()
                    ,passwordField.getText(),String.valueOf(Cart.getTotalAmount()),accNumField.getText());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int wagePercent = ManageInfo.allManagers.get(0).getWage();
            String withDrawMoney = String.valueOf(Cart.getTotalAmount()*(100-wagePercent)/100);
            String moveMoney = String.valueOf((Cart.getTotalAmount()*wagePercent)/100);
            MessageHandler.sendMoneyWithdrawMessage(usernameField.getText()
            ,passwordField.getText(),withDrawMoney,accNumField.getText());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MessageHandler.sendMoneyMoveMessage(usernameField.getText()
                    ,passwordField.getText(),moveMoney,accNumField.getText(),ManageInfo.allManagers.get(0).getBankAccountNumber());
            PayController.payController.finishBuying();
        } catch (WalletExceptions walletExceptions) {
            Functions.showDialog(walletExceptions.getMessage(), true);
        }
    }
}
