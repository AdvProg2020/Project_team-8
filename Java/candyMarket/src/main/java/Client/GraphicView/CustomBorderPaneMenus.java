package Client.GraphicView;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class CustomBorderPaneMenus {
    @FXML
    protected Pane centerPane;
    public void setCenter(String fxml){
        Pane pane = MenuHandler.getPaneByName(fxml);
        centerPane.getChildren().clear();
        centerPane.getChildren().add(pane);
    }
}
