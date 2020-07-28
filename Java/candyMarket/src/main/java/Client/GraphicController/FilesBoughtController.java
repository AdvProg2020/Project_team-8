package Client.GraphicController;

import Client.GraphicView.CustomFileDownloadBox;
import Client.Model.FileGood;
import Client.Model.Good;
import Client.Model.UserHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FilesBoughtController implements Initializable {
    @FXML private ScrollPane scrollPane;

    private ArrayList<CustomFileDownloadBox> downloadBoxes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        downloadBoxes = createDownloadBoxes();
        vBox.getChildren().addAll(downloadBoxes);
        scrollPane.setContent(vBox);
    }

    private ArrayList<CustomFileDownloadBox> createDownloadBoxes() {
        ArrayList<CustomFileDownloadBox> customFileDownloadBoxes = new ArrayList<>();
        List<Good> goods = UserHandler.currentBuyer.getBoughtGoods();
        for (Good good : goods) {
            if (good instanceof FileGood) {
                customFileDownloadBoxes.add(new CustomFileDownloadBox(((FileGood)good).getFileName()));
            }
        }

        return customFileDownloadBoxes;
    }
}
