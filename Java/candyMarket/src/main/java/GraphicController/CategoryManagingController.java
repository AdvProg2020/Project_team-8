package GraphicController;

import GraphicView.MenuHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManagingController implements Initializable {
    public Button editCategoryBtn;
    public Button deleteCategoryBtn;

    public TableView<Category> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<Category> tableView) {
        this.tableView = tableView;
    }

    @FXML private TableView<Category> tableView;
    @FXML private TableColumn<Category, String> nameColumn;
    @FXML private TableColumn<Category, List<String>> propertiesColumn;

    @FXML private TextField categoryNameField;
    private boolean editMode;
    public static CategoryManagingController categoryManagingController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryManagingController=this;
        nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        propertiesColumn.setCellValueFactory(new PropertyValueFactory<Category, List<String>>("specialAttributes"));
        tableView.setItems(getCategories());
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public String getCategoryName(){
        return categoryNameField.getText();
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
            UserHandler.currentManager.removeCategory(category);
            allCategories.remove(category);
        }
    }


    public void userClickedOnTable(MouseEvent mouseEvent) {
    }


    public void changeCategoryNameCellEvent(TableColumn.CellEditEvent<Category, String> editedCell) {
        Category category = tableView.getSelectionModel().getSelectedItem();
        category.setName(editedCell.getNewValue());
    }


    public void newCategoryButtonPushed(ActionEvent actionEvent) {
        editMode = false;
        if(categoryNameField.getText().equals(""))
            Functions.showDialog("please enter category name",true);
        else
            MenuHandler.createStageWithScene("CategoryAddProperties");
    }

    public void onMousePressed(MouseEvent mouseEvent) {
        if(tableView.getSelectionModel().getSelectedItem()!=null){
            editCategoryBtn.setDisable(false);
            deleteCategoryBtn.setDisable(false);
        }
    }
    public void editCategoryClick(ActionEvent actionEvent) {
        editMode = true;
        MenuHandler.createStageWithScene("CategoryAddProperties");
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}
