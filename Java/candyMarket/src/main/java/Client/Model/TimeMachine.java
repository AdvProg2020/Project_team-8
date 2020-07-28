package Client.Model;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TimeMachine {
    public static class reloadPageThread extends Thread{
        Initializable initializable ;
        public reloadPageThread(Initializable initializable){
            this.initializable = initializable;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    initializable.initialize(null, null);
                });
            }
        }
    }
}
