package GraphicController;

import Model.User;
import Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProfileController implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;

    @FXML private Label errorMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(UserHandler.getCurrentUser().getUsername());
        password.setText(UserHandler.getCurrentUser().getPassword());
        firstName.setText(UserHandler.getCurrentUser().getFirstName());
        lastName.setText(UserHandler.getCurrentUser().getLastName());
        email.setText(UserHandler.getCurrentUser().getEmail());
        phoneNumber.setText(UserHandler.getCurrentUser().getPhoneNumber());

        errorMessage.setText("");
    }


    public void editing(ActionEvent actionEvent) {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String emailText = email.getText();
        String phoneNumberText = phoneNumber.getText();

        errorMessage.setStyle("-fx-background-color: #ff0000;");

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
        } else {
            errorMessage.setStyle("-fx-background-color: #00ff00;");
            errorMessage.setText("Updated");
            UserHandler.getCurrentUser().setUsername(usernameText);
            UserHandler.getCurrentUser().setPassword(passwordText);
            UserHandler.getCurrentUser().setFirstName(firstNameText);
            UserHandler.getCurrentUser().setLastName(lastNameText);
            UserHandler.getCurrentUser().setEmail(emailText);
            UserHandler.getCurrentUser().setPhoneNumber(phoneNumberText);
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
