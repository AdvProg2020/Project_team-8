package GraphicController;

import Model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.*;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestViewController implements Initializable {

    public Label info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        info.setText(Request.getRequestById(ManagerRequestController.getId()).viewInfo());
    }

    public void acceptRequest(ActionEvent actionEvent) {
    }

    public void declineRequest(ActionEvent actionEvent) {
    }
}
