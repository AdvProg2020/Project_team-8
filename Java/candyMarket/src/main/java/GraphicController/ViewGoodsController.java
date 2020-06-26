package GraphicController;

import GraphicView.CustomButton;
import GraphicView.CustomGoodBox;
import GraphicView.MenuHandler;
import Model.Filter;
import Model.FilterAndSort;
import Model.Good;
import Model.ManageInfo;
import View.MainMenu;
import View.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    ArrayList<CustomGoodBox> goodBoxes = new ArrayList<>();
    ArrayList<Good> goods = new ArrayList<>();
    @FXML
    ScrollPane scrollPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goods = ManageInfo.allGoods;
        sortChoiceBox.getItems().addAll(FilterAndSort.getSortsType());
        sortChoiceBox.setValue("DATE_CREATED");
        setScrollPane();
    }
    private void setScrollPane(){
        goods = FilterAndSort.sortGoods(goods);
        goodBoxes = createGoodBoxes(goods);
        for(int i=0;i<goodBoxes.size();i+=2){
            HBox hBox = new HBox();
            hBox.getChildren().add(goodBoxes.get(i));
            if(i+1<goodBoxes.size())
                hBox.getChildren().add(goodBoxes.get(i+1));
            scrollPane.setContent(hBox);
        }
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
    public void onUpdateListBtnClick(MouseEvent mouseEvent) {
        for (FilterAndSort.sortsTypes s:
                FilterAndSort.sortsTypes.values()) {
            if(s.toString().equals(sortChoiceBox.getValue()))
                FilterAndSort.sortsType = s;
                }
        setScrollPane();
    }
}
