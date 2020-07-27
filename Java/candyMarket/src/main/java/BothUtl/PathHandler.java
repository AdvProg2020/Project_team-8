package BothUtl;

import javafx.scene.media.Media;

import java.io.File;
import java.net.MalformedURLException;

public class PathHandler {
    public static String srcPath = (System.getProperty("user.dir")+"\\Java\\candyMarket\\src\\");
    public static String srcUrl;

    static {
        try {
            srcUrl = String.valueOf((new File(srcPath).toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static String resourcePath = (srcPath+"main\\resources\\");
    public static String resourceURL;

    static {
        try {
            resourceURL = String.valueOf(new File(resourcePath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static Media buttonClickMedia;
    public static Media managerMenuMedia;
    public static Media buyerMenuMedia;
    public static Media sellerMenuMedia;

    static {
        try {
            buttonClickMedia = new Media(new File(resourcePath+"Musics\\clickButton.mp3").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
             managerMenuMedia = new Media(new File(resourcePath+"Musics\\ManagerMenuMusic.mp3").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            buyerMenuMedia = new Media(new File(resourcePath+"Musics\\BuyerMenuMusic.mp3").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            sellerMenuMedia = new Media(new File(resourcePath+"Musics\\SellerMenuMusic.mp3").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\Client\\GraphicView\\";
    public static String userWithoutImageUrl;
    static {
        try {
            userWithoutImageUrl = String.valueOf(new File(resourcePath + "Photos\\UserWithoutImage.png").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static String withoutImageUrl;
    static {
        try {
            withoutImageUrl = String.valueOf(new File(resourcePath + "Photos\\WithoutImage.png").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
