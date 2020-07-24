package Client.GraphicController;

import Client.Model.Good;
import Client.Model.ManageInfo;
import BothUtl.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CompareGoodsController implements Initializable {
    @FXML private TextField otherGoodField;
    @FXML private VBox primeGoodPrimeAttributes;
    @FXML private VBox secondaryGoodPrimeAttributes;
    @FXML private ScrollPane primeGoodScrollPane;
    @FXML private ScrollPane secondaryGoodScrollPane;

    private Good primeGood;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        primeGood = GoodMenuController.goodMenuController.getGood();
        createSummaryProperties();
        createSpecialProperties();
    }

    public void setComparision(ActionEvent actionEvent) {
        String otherGood = otherGoodField.getText();
        Good good = null;
        for (Good allGood : ManageInfo.allGoods) {
            if (allGood.getName().equals(otherGood)) {
                good = allGood;
                break;
            }
        }
        if (good == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Such Good Exists!");
            alert.show();
        } else {
            secondaryGoodPrimeAttributes.getChildren().clear();
            Image image = new Image(String.valueOf(new File(PathHandler.withoutImageUrl)));
            if (good.hasImage())
                image = new Image(good.getImage());
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setImage(image);
            Label nameLbl = new Label("Name : "+good.getName());
            Label priceLbl = new Label("Price : "+good.getPrice());
            Label scoreLbl = new Label("Score : "+good.getAverageScore());
            Label stockLbl = new Label("Stock : "+good.getStock());
            Label sellerNameLbl = new Label("Seller Name : "+good.getSeller().getUsername());
            nameLbl.setFont(Font.font ("Verdana", 20));
            priceLbl.setFont(Font.font ("Verdana", 20));
            scoreLbl.setFont(Font.font ("Verdana", 20));
            stockLbl.setFont(Font.font ("Verdana", 20));
            sellerNameLbl.setFont(Font.font ("Verdana", 20));
            secondaryGoodPrimeAttributes.setAlignment(Pos.TOP_CENTER);
            secondaryGoodPrimeAttributes.getChildren().addAll(imageView,nameLbl,priceLbl,scoreLbl,stockLbl,sellerNameLbl);
            VBox vBox = new VBox();
            vBox.setMinWidth(311);
            vBox.setMaxWidth(311);
            for (String s: good.getSpecialAttributes()) {
                String categoryAttribute = good.getCategory().getSpecialAttributes().get(good.getSpecialAttributes().indexOf(s));
                Label label = new Label(categoryAttribute + "     :     " + s);
                label.setFont(Font.font("Verdana", 20));
                vBox.getChildren().add(label);
            }
            vBox.setAlignment(Pos.TOP_CENTER);
            secondaryGoodScrollPane.setContent(vBox);
        }
    }

    private void createSummaryProperties(){
        Image image = new Image(String.valueOf(new File(PathHandler.withoutImageUrl)));
        if (primeGood.hasImage())
            image = new Image(primeGood.getImage());
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setImage(image);
        Label nameLbl = new Label("Name : "+primeGood.getName());
        Label priceLbl = new Label("Price : "+primeGood.getPrice());
        Label scoreLbl = new Label("Score : "+primeGood.getAverageScore());
        Label stockLbl = new Label("Stock : "+primeGood.getStock());
        Label sellerNameLbl = new Label("Seller Name : "+primeGood.getSeller().getUsername());
        nameLbl.setFont(Font.font ("Verdana", 20));
        priceLbl.setFont(Font.font ("Verdana", 20));
        scoreLbl.setFont(Font.font ("Verdana", 20));
        stockLbl.setFont(Font.font ("Verdana", 20));
        sellerNameLbl.setFont(Font.font ("Verdana", 20));
        primeGoodPrimeAttributes.setAlignment(Pos.TOP_CENTER);
        primeGoodPrimeAttributes.getChildren().addAll(imageView,nameLbl,priceLbl,scoreLbl,stockLbl,sellerNameLbl);
    }
    private void createSpecialProperties(){
        VBox vBox = new VBox();
        vBox.setMinWidth(311);
        vBox.setMaxWidth(311);
        for (String s:
                primeGood.getSpecialAttributes()) {
            String categoryAttribute = primeGood.getCategory().getSpecialAttributes().get(primeGood.getSpecialAttributes().indexOf(s));
            Label label = new Label(categoryAttribute+ "     :     " +s);
            label.setFont(Font.font ("Verdana", 20));
            vBox.getChildren().add(label);
        }
        vBox.setAlignment(Pos.TOP_CENTER);
        primeGoodScrollPane.setContent(vBox);
    }
}
