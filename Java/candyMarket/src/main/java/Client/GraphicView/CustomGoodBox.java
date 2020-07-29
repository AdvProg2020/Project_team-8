package Client.GraphicView;

import Client.GraphicController.GoodMenuController;
import Client.Model.Good;
import BothUtl.PathHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import org.controlsfx.control.Rating;

import java.io.File;
import java.net.MalformedURLException;

public class CustomGoodBox extends VBox {
    ImageView photoIMG;
    Label nameLBL;
    Label priceLBL;
    //Rating ratingBAR;
    Label scoreLBL;
    Button viewBtn;
    Good good;
    private void openStage(){
        GoodMenuController.staticGood = good;
        GoodMenuController.staticSummaryGoodPropertiesVBox = this;
        MenuHandler.createStageWithScene("GoodMenu");
    }
    public CustomGoodBox(String name,int score,int price,Image image){
        good = Good.getGoodByName(name);
        photoIMG = new ImageView();
        photoIMG.setFitHeight(100);
        photoIMG.setFitHeight(100);
        nameLBL = new Label();
        priceLBL = new Label();
        scoreLBL = new Label();
        viewBtn = new Button("View More");
        viewBtn.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    openStage();
            }
        });
        photoIMG.setImage(image);
        ImageView soldOutImageView = null;
        if (good.getStock() == 0) {
            soldOutImageView = new ImageView();
            try {
                String url = String.valueOf(new File(PathHandler.resourcePath+"Photos\\SoldOut.png").toURI().toURL());
            } catch (MalformedURLException e) {
                System.out.println("invalidPath");;
            }
            soldOutImageView.setImage(new Image(PathHandler.soldOutImageUrl));
            soldOutImageView.setFitHeight(100);
            soldOutImageView.setFitWidth(100);
        }
        AnchorPane imagePane = new AnchorPane();
        imagePane.setMaxHeight(100);
        imagePane.setMaxWidth(100);
        imagePane.setMinWidth(100);
        imagePane.setMinHeight(100);
        if (soldOutImageView == null) {
            imagePane.getChildren().add(photoIMG);
        } else {
            imagePane.getChildren().addAll(photoIMG, soldOutImageView);
            soldOutImageView.toFront();
        }
        nameLBL.setText("GoodName : "+name);
        priceLBL.setText("Price : "+String.valueOf(price));
        scoreLBL.setText("Score : "+String.valueOf(score));
        ///ratingBAR.setRating(score);
        getChildren().addAll(imagePane,nameLBL,priceLBL,scoreLBL,viewBtn);
    }
    public CustomGoodBox(String name,int score,int price){
        good = Good.getGoodByName(name);
        photoIMG = new ImageView();
        photoIMG.setFitWidth(100);
        photoIMG.setFitHeight(100);
        nameLBL = new Label();
        priceLBL = new Label();
        scoreLBL = new Label();
        String url = null;
        try {
            url = String.valueOf(new File(PathHandler.resourcePath+"Photos\\WithoutImage.png").toURI().toURL());
        } catch (MalformedURLException e) {
            System.out.println("invalidPath");;
        }
        photoIMG.setImage(new Image(PathHandler.withoutImageUrl));
        ImageView soldOutImageView = null;
        if (good.getStock() == 0) {
            soldOutImageView = new ImageView();
            try {
                url = String.valueOf(new File(PathHandler.resourcePath+"Photos\\SoldOut.png").toURI().toURL());
            } catch (MalformedURLException e) {
                System.out.println("invalidPath");;
            }
            soldOutImageView.setImage(new Image(PathHandler.soldOutImageUrl));
            soldOutImageView.setFitHeight(100);
            soldOutImageView.setFitWidth(100);
        }
        AnchorPane imagePane = new AnchorPane();
        imagePane.setMaxHeight(100);
        imagePane.setMaxWidth(100);
        imagePane.setMinWidth(100);
        imagePane.setMinHeight(100);
        if (soldOutImageView == null) {
            imagePane.getChildren().add(photoIMG);
        } else {
            imagePane.getChildren().addAll(photoIMG, soldOutImageView);
            soldOutImageView.toFront();
        }
        nameLBL.setText("GoodName : "+name);
        priceLBL.setText("Price : "+String.valueOf(price));
        scoreLBL.setText("Score : "+String.valueOf(score));
        viewBtn = new Button("View More");
        viewBtn.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                openStage();
            }
        });
        ///ratingBAR.setRating(score);
        getChildren().addAll(imagePane,nameLBL,priceLBL,scoreLBL,viewBtn);
    }
}
