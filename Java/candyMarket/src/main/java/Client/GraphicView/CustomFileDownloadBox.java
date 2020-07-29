package Client.GraphicView;

import Client.DataHandler.MessageHandler;
import Client.GraphicController.Functions;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;

public class CustomFileDownloadBox extends HBox {
    private Label fileName;
    private Button downloadBtn;

    public CustomFileDownloadBox(String file) {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(20);
        this.setMaxWidth(344);

        HBox hBox1 = new HBox();
        hBox1.setMaxWidth(310);
        hBox1.setAlignment(Pos.CENTER_LEFT);

        fileName = new Label();
        fileName.setText(" " + file);
        fileName.setFont(Font.font("System Bold", 20));

        hBox1.getChildren().addAll(fileName);

        downloadBtn = new Button();
        downloadBtn.setText("download");
        downloadBtn.setTextFill(Color.WHITE);
        downloadBtn.setStyle("-fx-background-color: #22226f");
        downloadBtn.setOnAction(e -> {
            try {
                MessageHandler.sendDownloadDataMessage(System.getProperty("user.home")+"\\Downloads\\",file);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                Functions.showDialog("successfully file downloaded",false);
            } catch (IOException ioException) {
                Functions.showDialog("cant download file",true);
            }
        });

        this.getChildren().addAll(hBox1, downloadBtn);
    }
}
