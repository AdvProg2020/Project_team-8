package GraphicController;

import GraphicView.CustomButton;
import GraphicView.CustomGoodBox;
import GraphicView.MenuHandler;
import Model.*;
import View.MainMenu;
import View.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ViewGoodsController implements Initializable {
    public ChoiceBox sortChoiceBox;
    public Button updateListBtn;
    public ScrollPane goodsScrollPane;
    public ScrollPane categoriesScrollPane;
    ArrayList<CustomGoodBox> goodBoxes = new ArrayList<>();
    ArrayList<Good> goods = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goods = ManageInfo.allGoods;
        sortChoiceBox.getItems().addAll(FilterAndSort.getSortsType());
        sortChoiceBox.setValue("DATE_CREATED");
        setGoodsScrollPane();
        setCategoryScrollPane();
    }
    private void setCategoryScrollPane(){
        ArrayList<Category> categories = ManageInfo.allCategories;
        ArrayList<CheckBox> checkBoxes = createCheckBoxes(categories);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        for (CheckBox c:
             checkBoxes) {
            vBox.getChildren().add(c);
        }
        categoriesScrollPane.setContent(vBox);
    }
    private void setGoodsScrollPane(){
        VBox allGoods = new VBox();
        goods = FilterAndSort.sortGoods(goods);
        goodBoxes = createGoodBoxes(goods);
        for(int i=0;i<goodBoxes.size();i+=2){
            HBox hBox = new HBox();
            hBox.setSpacing(200);
            hBox.getChildren().add(goodBoxes.get(i));
            if(i+1<goodBoxes.size())
                hBox.getChildren().add(goodBoxes.get(i+1));
            allGoods.getChildren().add(hBox);
        }
        goodsScrollPane.setContent(allGoods);
    }
    private ArrayList<CustomGoodBox> createGoodBoxes(ArrayList<Good> goods){
        ArrayList<CustomGoodBox> customGoodBoxes = new ArrayList<>();
        for (Good g:
             goods) {
            CustomGoodBox goodBox;
            if(g.hasImage()) {
                goodBox = new CustomGoodBox(g.getName(), g.getAverageScore(), g.getPrice(), g.getImage());
            }
            else goodBox = new CustomGoodBox(g.getName(), g.getAverageScore(), g.getPrice());
            customGoodBoxes.add(goodBox);
        }
        return customGoodBoxes;
    }
    private ArrayList<CheckBox> createCheckBoxes(ArrayList<Category> categories){
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        for (Category c:
             categories) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(c.getName());
            checkBoxes.add(checkBox);
        }
        return checkBoxes;
    }
    public void onUpdateListBtnClick(MouseEvent mouseEvent) {
        for (FilterAndSort.sortsTypes s:
                FilterAndSort.sortsTypes.values()) {
            if(s.toString().equals(sortChoiceBox.getValue()))
                FilterAndSort.sortsType = s;
                }
        setGoodsScrollPane();
    }
}
