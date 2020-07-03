package GraphicController;

import Model.ManageInfo;
import Model.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerRequestController implements Initializable {
    @FXML private TableView<Request> tableView;
    @FXML private TableColumn<Request, String> requestCommand;
    @FXML private TableColumn<Request, String> requestState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();
    }

    private void createTable() {
        Request.setSellerRequest();
        requestCommand.setCellValueFactory(new PropertyValueFactory<Request, String>("requestCommand"));
        requestState.setCellValueFactory(new PropertyValueFactory<Request, String>("stateString"));

        tableView.setItems(getRequests());
    }

    private ObservableList<Request> getRequests() {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        requests.addAll(Request.sellersRequest);

        return requests;
    }
}
