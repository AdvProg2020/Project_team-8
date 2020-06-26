package GraphicView;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import org.controlsfx.control.Rating;

import java.awt.*;
import java.io.File;
import java.net.URL;
public class CustomGoodBox extends VBox {
    ImageView photoIMG;
    Label nameLBL;
    Label priceLBL;
    //Rating ratingBAR;
    Label scoreLBL;
    public  CustomGoodBox(String name,int score,int price,Image image){
        photoIMG.setImage(image);
        nameLBL.setText(name);
        priceLBL.setText(String.valueOf(price));
        scoreLBL.setText(String.valueOf(score));
        ///ratingBAR.setRating(score);
        getChildren().addAll(photoIMG,nameLBL,priceLBL,scoreLBL);
    }
    public  CustomGoodBox(String name,int score,int price){
        URL url = getClass().getResource("/Photos/WithoutImage.png");
        photoIMG.setImage(new Image(String.valueOf(url)));
        nameLBL.setText(name);
        priceLBL.setText(String.valueOf(price));
        scoreLBL.setText(String.valueOf(score));
        ///ratingBAR.setRating(score);
        getChildren().addAll(photoIMG,nameLBL,priceLBL,scoreLBL);
    }
}
