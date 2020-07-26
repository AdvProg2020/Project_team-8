package Client.GraphicController;

import Client.GraphicView.MenuHandler;
import Client.Model.Comment;
import BothUtl.PathHandler;
import Client.Model.Cart;
import Client.Model.Good;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GoodMenuController implements Initializable {
    @FXML public VBox specialGoodPropertiesVBox;
    @FXML public VBox summaryGoodPropertiesVBox;

    @FXML private Pane movieBox;
    @FXML private MediaView mv;

    public static GoodMenuController goodMenuController;
    private Good good;
    public static Good staticGood;
    public static VBox staticSummaryGoodPropertiesVBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goodMenuController = this;
        this.good = staticGood;
        createSpecialProperties();
        createSummaryProperties();
    }
    private void createSummaryProperties(){
        Image image = new Image(String.valueOf(new File(PathHandler.withoutImageUrl)));
        if (good.hasImage())
            image = new Image(good.getImage());
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setImage(image);
        Media media;
        if (good.hasMovie()) {
            media = new Media(new File(good.getMovie()).toURI().toString());
            MediaPlayer mp = new MediaPlayer(media);
            mv = new MediaView(mp);
            var ref = new Object() {
                boolean playing = false;
            };
            mv.setOnMouseClicked(e -> {
                if (!ref.playing) {
                    mp.play();
                    ref.playing = true;
                }
                else {
                    mp.pause();
                    ref.playing = false;
                }
            });
            movieBox.setMaxWidth(275);
            movieBox.setMaxHeight(170);
            movieBox.getChildren().removeAll();
            movieBox.getChildren().add(mv);
        }
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
        summaryGoodPropertiesVBox.getChildren().addAll(imageView,nameLbl,priceLbl,scoreLbl,stockLbl,sellerNameLbl);
    }
    private void createSpecialProperties(){
        for (String s:
             good.getSpecialAttributes()) {
            String categoryAttribute = good.getCategory().getSpecialAttributes().get(good.getSpecialAttributes().indexOf(s));
            Label label = new Label(categoryAttribute+ "     :     " +s);
            label.setFont(Font.font ("Verdana", 20));
            specialGoodPropertiesVBox.getChildren().add(label);
        }
    }
    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public void addToCartClick(ActionEvent actionEvent) {
        if(good.getStock()<=0){
            Functions.showDialog("out of stock",true);
        }else {
            Cart.addGood(good);
            Functions.showDialog("Added Successfully", false);
        }
    }

    public void commentsClicked(ActionEvent actionEvent) {
        Comment.sortComments();
        MenuHandler.createStageWithScene("CommentsPage");
    }

    public void openComparePage(ActionEvent actionEvent) {
        MenuHandler.createStageWithScene("CompareGoods");
    }
}
