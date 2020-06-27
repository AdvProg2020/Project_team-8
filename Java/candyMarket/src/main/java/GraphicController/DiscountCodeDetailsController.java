package GraphicController;

import Model.Discount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DiscountCodeDetailsController implements Initializable {
    private Discount discount;

    @FXML private TextField code;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TextField percentage;
    @FXML private TextField max;
    @FXML private TextField usage;

    @FXML private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        discount = DiscountCodeManagingController.discountCodeManagingController.getDiscount();

        code.setText(Integer.toString(discount.getCode()));
        percentage.setText(Integer.toString(discount.getPercentReduction()));
        max.setText(Integer.toString(discount.getMaxReductionAmount()));
        usage.setText(Integer.toString(discount.getUsageNumber()));
        startDate.setValue(discount.getStartDate());
        endDate.setValue(discount.getEndDate());

        errorMessage.setText("");
    }

    public void editing(ActionEvent actionEvent) {
        try {
            int codeText = Integer.parseInt(code.getText());
            int percentageText = Integer.parseInt(percentage.getText());
            int maxText = Integer.parseInt(max.getText());
            int usageText = Integer.parseInt(usage.getText());
            LocalDate initial = startDate.getValue();
            LocalDate end = endDate.getValue();
            errorMessage.setStyle("-fx-background-color: #00ff00;");
            errorMessage.setText("Updated");
            discount.setCode(codeText);
            discount.setStartDate(initial);
            discount.setEndDate(end);
            discount.setPercentReduction(percentageText);
            discount.setMaxReductionAmount(maxText);
            discount.setUsageNumber(usageText);
        }catch(Exception e){
            errorMessage.setStyle("-fx-background-color: #ff0000;");
            errorMessage.setText("enter a valid number");
        }
        DiscountCodeManagingController.discountCodeManagingController.createDiscountTable();
    }
}
