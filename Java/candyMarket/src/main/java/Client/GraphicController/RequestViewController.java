package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import Client.Model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

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
        MenuHandler.secondCurrentWindow.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Confirmed");
        alert.show();
    }

    public void declineRequest(ActionEvent actionEvent) {
        Request.getRequestById(ManagerRequestController.getId()).declineRequest();
        MenuHandler.secondCurrentWindow.close();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Declined");
        alert.show();
    }
}
