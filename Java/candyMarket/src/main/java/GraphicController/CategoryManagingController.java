package GraphicController;

import Model.Category;
import Model.Discount;
import Model.Good;
import Model.ManageInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CategoryManagingController implements Initializable {
    @FXML private TableView<Category> tableView;
    @FXML private TableColumn<Category, String> nameColumn;
    @FXML private TableColumn<Category, String> specialAttributesColumn;

    @FXML private TextField categoryNameField;
    @FXML private TextArea specialAttributesField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        specialAttributesColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("specialAttributes"));

        tableView.setItems(getCategories());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private ObservableList<Category> getCategories() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(ManageInfo.allCategories);

        return categories;
    }

    public void deleteButtonPushed(ActionEvent actionEvent) {
        ObservableList<Category> selectedRows, allCategories;
        allCategories = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Category category: selectedRows)
        {
            ManageInfo.allCategories.remove(category);
            allCategories.remove(category);
        }
    }

    public void newCodeButtonPushed()
    {
        if (categoryNameField.getText().length() > 0 && specialAttributesField.getText().length() > 0) {
            String categoryNameText = categoryNameField.getText();
            String specialAttributesText = specialAttributesField.getText();

            Category newCategory = new Category(categoryNameText, specialAttributesText);

            tableView.getItems().add(newCategory);
        }
    }
}
