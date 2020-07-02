package GraphicController;

import Model.Manager;
import Model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        Request.getRequestById(ManagerRequestController.getId()).acceptRequest();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("confirm");
        alert.show();
    }

    public void declineRequest(ActionEvent actionEvent) {
        Request.getRequestById(ManagerRequestController.getId()).declineRequest();
    }
}
