package Client.GraphicController;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GetSellerProperties implements Initializable {

    public TextField companyTXT;
    public static GetSellerProperties getSellerProperties;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSellerProperties = this;
    }
}
