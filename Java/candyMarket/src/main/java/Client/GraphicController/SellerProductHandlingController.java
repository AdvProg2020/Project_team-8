package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import BothUtl.PathHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import Client.Model.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerProductHandlingController implements Initializable {
    public static SellerProductHandlingController sellerProductHandlingController;
    public ImageView goodImg;
    public Button addProductBtn;
    public Button editProductBtn;
    public Button deleteProductBtn;

    @FXML private TableView<Good> tableView;
    @FXML private TableColumn<Good, String> productNameColumn;
    @FXML private TableColumn<Good, String> categoryColumn;
    @FXML private TableColumn<Good, Integer> priceColumn;

    public TableView<Good> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<Good> tableView) {
        this.tableView = tableView;
    }

    @FXML private Label errorMessage;
    @FXML private Button showBuyersButton;
    private String goodName;
    private String goodPrice;
    private String goodBrand;
    private String goodStock;
    private Category goodCategory;
    private String goodDetail;
    private Good currentGood;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sellerProductHandlingController = this;
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));
        tableView.setItems(getGoods());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public ObservableList<Good> getGoods()
    {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        goods.addAll(UserHandler.currentSeller.getGoods());

        return goods;
    }

    public void userClickedOnTable(MouseEvent mouseEvent) {
        if(tableView.getSelectionModel().getSelectedItem()==null){
            showBuyersButton.setDisable(true);
            deleteProductBtn.setDisable(true);
            editProductBtn.setDisable(true);
        }
        else {
            showBuyersButton.setDisable(false);
            deleteProductBtn.setDisable(false);
            editProductBtn.setDisable(false);
        }
    }

    public void deleteButtonPushed()
    {
        ObservableList<Good> selectedRows, allGoods;
        allGoods = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Good good: selectedRows)
        {
            new Request(Request.type.REMOVE_GOOD).createDeleteProductRequest(good);
        }
    }


    public void showBuyers(ActionEvent actionEvent) {
       MenuHandler.createStageWithScene("ShowBuyersOfGood");
    }

    public Good getGood() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void editing(ActionEvent actionEvent) {
        if(tableView.getSelectionModel().getSelectedItem()!=null) {
                    currentGood =tableView.getSelectionModel().getSelectedItem();
                    MenuHandler.createStageWithScene("GoodEditing");
        }
    }

    public void choosePhotoOnClick(ActionEvent actionEvent) {
        FileChooser fileChooser = Functions.prepareFileChooser();
        File selectedDir = fileChooser.showOpenDialog(MenuHandler.currentWindow);
        File imageFile = new File(selectedDir.getAbsolutePath());
        Image profileImage;
        profileImage = new Image(String.valueOf(Functions.changePathToUrl(String.valueOf(imageFile))));
        //setProductImage
        goodImg.setImage(profileImage);
    }

    public void deletePhotoOnClick(ActionEvent actionEvent) {
        Image image = new Image(PathHandler.withoutImageUrl);
        goodImg.setImage(image);
    }

    public void addProductOnClick(ActionEvent actionEvent) {
        currentGood = null;
        MenuHandler.createStageWithScene("GoodEditing");
    }

    public Good getCurrentGood() {
        return currentGood;
    }

    public void setCurrentGood(Good currentGood) {
        this.currentGood = currentGood;
    }

    public void addFile(ActionEvent actionEvent) {
        currentGood = null;
        MenuHandler.createStageWithScene("AddNewFile");
    }
}
