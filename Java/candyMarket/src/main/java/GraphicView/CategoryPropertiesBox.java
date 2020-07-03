package GraphicView;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CategoryPropertiesBox extends HBox {
    private Label propertyName;
    private TextField propertyValue;

    public CategoryPropertiesBox(Label propertyName, TextField propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.getChildren().addAll(propertyName,propertyValue);
        this.setSpacing(10);
    }

    public TextField getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(TextField propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Label getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(Label propertyName) {
        this.propertyName = propertyName;
    }
}
