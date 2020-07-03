package GraphicController;

import GraphicView.MenuHandler;
import Model.Discount;
import Model.ManageInfo;
import Model.Manager;
import Model.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerRequestController implements Initializable {
    public static ManagerRequestController managerRequestController;
    @FXML private TableView<Request> tableView;
    @FXML private TableColumn<Request, Integer> idColumn;
    @FXML private TableColumn<Request, String> requestCommandColumn;
    @FXML private TextField selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        managerRequestController = this;
        createTable();
    }

    public void createTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Request, Integer>("requestId"));
        requestCommandColumn.setCellValueFactory(new PropertyValueFactory<Request, String>("requestCommand"));

        tableView.setItems(getRequests());
    }

    private ObservableList<Request> getRequests() {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        requests.addAll(Request.getUncheckedRequests());

        return requests;
    }

    public void getRequestInfo(ActionEvent actionEvent) {
        MenuHandler.createStageWithScene("RequestView");
    }

    public static int getId() {
        return Integer.parseInt(managerRequestController.selectedId.getText());
    }
}
