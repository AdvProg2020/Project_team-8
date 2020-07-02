package GraphicController;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.boot.archive.internal.UrlInputStreamAccess;

import java.io.File;
import java.lang.reflect.Array;
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
    public static void showDialog(String text, boolean isError){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!isError)
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(text);
        alert.show();
    }
}
