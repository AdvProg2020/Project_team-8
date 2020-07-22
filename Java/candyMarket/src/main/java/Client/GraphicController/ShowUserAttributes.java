package Client.GraphicController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowUserAttributes implements Initializable {
    public static ShowUserAttributes showUserAttributes;
    public Label UserNameLBL;
    public Label NameLBL;
    public Label LastNameLBL;
    public Label EmailLBL;
    public Label PasswordLBL;
    public Label PhoneNumberLBL;
    public Label UserTypeLBL;
    public Label CompanyLBL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showUserAttributes =this;
        if(RegisterMenuController.registerMenuController.typeValue.equals("Seller"))
            setUserAttributesLabels(true);
        else setUserAttributesLabels(false);
    }
    private void setUserAttributesLabels(boolean isSeller){
        UserNameLBL.setText(UserNameLBL.getText()+RegisterMenuController.registerMenuController.usernameText);
        NameLBL.setText(NameLBL.getText()+RegisterMenuController.registerMenuController.firstNameText);
        PasswordLBL.setText(PasswordLBL.getText()+RegisterMenuController.registerMenuController.passwordText);
        EmailLBL.setText(EmailLBL.getText()+RegisterMenuController.registerMenuController.emailText);
        UserNameLBL.setText(UserNameLBL.getText()+RegisterMenuController.registerMenuController.usernameText);
        LastNameLBL.setText(LastNameLBL.getText()+RegisterMenuController.registerMenuController.lastNameText);
        PhoneNumberLBL.setText(PhoneNumberLBL.getText()+RegisterMenuController.registerMenuController.phoneNumberText);
        UserTypeLBL.setText(UserTypeLBL.getText()+RegisterMenuController.registerMenuController.typeValue);
        if(isSeller) {
            CompanyLBL.setVisible(true);
            CompanyLBL.setText(RegisterMenuController.registerMenuController.companyName);
        }else {
            CompanyLBL.setVisible(false);
        }
    }
}
