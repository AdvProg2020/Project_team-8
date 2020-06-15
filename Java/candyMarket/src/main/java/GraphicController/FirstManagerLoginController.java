package GraphicController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class FirstManagerLoginController implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;

    @FXML private Label errorMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessage.setText("");
    }


    public void registering(ActionEvent actionEvent) {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String emailText = email.getText();
        String phoneNumberText = phoneNumber.getText();

        if (usernameText.length() == 0 || passwordText.length() == 0 ||
        firstNameText.length() == 0 || lastNameText.length() == 0 ||
        emailText.length() == 0 || phoneNumberText.length() == 0) {
            errorMessage.setText("Please complete all fields");
        }
        else if (!emailValidation(emailText)) {
            errorMessage.setText("Please enter a correct form of E-mail");
        }
        else if (!phoneValidation(phoneNumberText)) {
            errorMessage.setText("Please enter a correct form of phone number");
        }
        else {
            errorMessage.setText("");
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
