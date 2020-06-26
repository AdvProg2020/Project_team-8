package GraphicController;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsProfileForManagerController implements Initializable {
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> firstNameColumn;
    @FXML private TableColumn<User, String> lastNameColumn;
    @FXML private TableColumn<User, User.UserType> typeColumn;

    @FXML private Button detailedEditClientButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<User, User.UserType>("type"));

        tableView.setItems(getClients());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.detailedEditClientButton.setDisable(true);
    }

    public ObservableList<User> getClients()
    {
        ObservableList<User> client = FXCollections.observableArrayList();
        client.addAll(User.getUsers());

        return client;
    }

    public void deleteButtonPushed()
    {
        ObservableList<User> selectedRows, allPeople;
        allPeople = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (User user: selectedRows)
        {
            User.deleteUser(User.getUserByUsername(user.getUsername()));
            allPeople.remove(user);
        }
    }

    public void userClickedOnTable(MouseEvent mouseEvent) {
        this.detailedEditClientButton.setDisable(false);
    }

    public void changeSceneToDetailedPersonView(ActionEvent actionEvent) {
        //??
    }
}
