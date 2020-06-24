package GraphicController;

import GraphicView.MenuHandler;
import Model.Buyer;
import Model.Seller;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterMenuController implements Initializable {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private ChoiceBox type;

    @FXML private Label errorMessage;
    public static RegisterMenuController registerMenuController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stepType = step.GETUSERINFO;
        errorMessage.setText("");
        registerMenuController = this;
    }
    public static enum step{
        GETUSERINFO,GETSELLERINFO,SHOWINFO
    }
     step stepType;
     String usernameText;
     String passwordText;
     String firstNameText;
     String lastNameText;
     String emailText;
     String phoneNumberText;
     String typeValue;
     String companyName;
    public void registering(ActionEvent actionEvent) {
        if(stepType == step.GETUSERINFO) {
            usernameText = username.getText();
            passwordText = password.getText();
            firstNameText = firstName.getText();
            lastNameText = lastName.getText();
            emailText = email.getText();
            phoneNumberText = phoneNumber.getText();
            typeValue = type.getValue().toString();

            if (usernameText.length() == 0 || passwordText.length() == 0 ||
                    firstNameText.length() == 0 || lastNameText.length() == 0 ||
                    emailText.length() == 0 || phoneNumberText.length() == 0) {
                errorMessage.setText("Please complete all fields");
            } else if (User.isThereUserWithUsername(usernameText)) {
                errorMessage.setText("Username already exists, please try again");
            } else if (!emailValidation(emailText)) {
                errorMessage.setText("Please enter a correct form of E-mail");
            } else if (!phoneValidation(phoneNumberText)) {
                errorMessage.setText("Please enter a correct form of phone number");
            }
            else if(type.getValue().toString().equals("Select")) {
                errorMessage.setText("Please set the type of your user");
            }
             else {
                errorMessage.setText("");
                if (typeValue.equals("Buyer")) {
                    MenuHandler.changeScene("ShowUserProperties", MenuHandler.secondCurrentWindow);
                    stepType = step.SHOWINFO;
                } else {
                    MenuHandler.changeScene("getSellerProperties", MenuHandler.secondCurrentWindow);
                    stepType  = step.GETSELLERINFO;
                }
            }
        }
        else if(stepType == step.GETSELLERINFO){
            if(GetSellerProperties.getSellerProperties.companyTXT.getText().equals("")) return;
            companyName = GetSellerProperties.getSellerProperties.companyTXT.getText();
            MenuHandler.changeScene("ShowUserAttributes",MenuHandler.secondCurrentWindow);
            RegisterMenuController.registerMenuController.stepType = RegisterMenuController.step.SHOWINFO;
        }
        else {
            if(typeValue.equals("Seller"))
                new Seller(usernameText, firstNameText, lastNameText, emailText, phoneNumberText, passwordText,companyName);
            else new Buyer(usernameText, firstNameText, lastNameText, emailText, phoneNumberText, passwordText);
            MenuHandler.secondCurrentWindow.close();
        }
    }

    private boolean emailValidation(String email) {
        String mailRegex = "^\\S+@\\w+\\.com$";
        Pattern mailPattern = Pattern.compile(mailRegex);
        return email.matches(mailRegex);
    }

    private boolean phoneValidation(String phone) {
        String phoneRegex = "^\\+?\\d\\d\\d\\d+$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        return phone.matches(phoneRegex);
    }
}