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

public class ManagerProfileEditingController implements Initializable {
    User user = ClientsProfileForManagerController.clientsProfileForManagerController.getUser();
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;

    @FXML private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());

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
        } else if (!emailValidation(emailText)) {
            errorMessage.setText("Please enter a correct form of E-mail");
        } else if (!phoneValidation(phoneNumberText)) {
            errorMessage.setText("Please enter a correct form of phone number");
        } else {
            errorMessage.setStyle("-fx-background-color: #00ff00;");
            errorMessage.setText("Updated");
            user.setUsername(usernameText);
            user.setPassword(passwordText);
            user.setFirstName(firstNameText);
            user.setLastName(lastNameText);
            user.setEmail(emailText);
            user.setPhoneNumber(phoneNumberText);
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
