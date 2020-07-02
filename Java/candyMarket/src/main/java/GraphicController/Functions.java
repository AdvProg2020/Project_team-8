package GraphicController;

import javafx.stage.FileChooser;
import org.hibernate.boot.archive.internal.UrlInputStreamAccess;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Functions {
    public static FileChooser prepareFileChooser(){
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        fileChooser.setSelectedExtensionFilter(imageFilter);
        return fileChooser;
    }
    public static URL changePathToUrl(String path){
        URL url = null;
        try {
            String urlString = String.valueOf(new File(path).toURI().toURL());
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
