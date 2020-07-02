package GraphicView;

import java.io.File;
import java.net.MalformedURLException;

public class PathHandler {
    public static String srcPath = (System.getProperty("user.dir")+"\\Java\\candyMarket\\src\\");
    public static String resourcePath = (srcPath+"main\\resources\\");
    public static String resourceURL = String.valueOf((PathHandler.class.getResource("")));
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\GraphicView\\";
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
