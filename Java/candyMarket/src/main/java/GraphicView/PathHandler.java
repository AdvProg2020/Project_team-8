package GraphicView;

public class PathHandler {
    public static String srcPath = (System.getProperty("user.dir")+"\\Java\\candyMarket\\src\\");
    public static String resourcePath = (srcPath+"main\\resources\\");
    public static String resourceURL = String.valueOf((PathHandler.class.getResource("")));
    public static String fxmlPath = "Java\\candyMarket\\src" +
            "\\main\\java\\GraphicView\\";
}
