package GraphicView;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import org.controlsfx.control.Rating;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class CustomGoodBox extends VBox {
    ImageView photoIMG;
    Label nameLBL;
    Label priceLBL;
    //Rating ratingBAR;
    Label scoreLBL;
    public CustomGoodBox(String name,int score,int price,Image image){
        photoIMG = new ImageView();
        photoIMG.setFitWidth(100);
        photoIMG.setFitHeight(100);
        nameLBL = new Label();
        priceLBL = new Label();
        scoreLBL = new Label();
        photoIMG.setImage(image);
        nameLBL.setText("GoodName : "+name);
        priceLBL.setText("Price : "+String.valueOf(price));
        scoreLBL.setText("Score : "+String.valueOf(score));
        ///ratingBAR.setRating(score);
        getChildren().addAll(photoIMG,nameLBL,priceLBL,scoreLBL);
    }
    public CustomGoodBox(String name,int score,int price){
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
        nameLBL.setText("GoodName : "+name);
        priceLBL.setText("Price : "+String.valueOf(price));
        scoreLBL.setText("Score : "+String.valueOf(score));
        ///ratingBAR.setRating(score);
        getChildren().addAll(photoIMG,nameLBL,priceLBL,scoreLBL);
    }
}
