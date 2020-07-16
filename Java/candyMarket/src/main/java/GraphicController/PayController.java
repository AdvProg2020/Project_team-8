package GraphicController;

import GraphicView.MenuHandler;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PayController implements Initializable {
    @FXML private TextField phoneField;
    @FXML private TextArea addressField;
    @FXML private TextField discountField;

    @FXML private Button discountBtn;

    @FXML private Label totalAmountLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cart.setTotalAmount();
        totalAmountLabel.setText(Integer.toString(Cart.getTotalAmount()));
    }


    public void setDiscountCode(ActionEvent actionEvent) {
        ArrayList<Discount> discounts = UserHandler.currentBuyer.getMyDiscounts();
        try {
            int codeText = Integer.parseInt(discountField.getText());
            boolean hasCode = false;
            for (Discount discount : discounts) {
                if (discount.getCode() == codeText) {
                    if (discount.getUsageNumber() <= 0) {
                        Functions.showDialog("you have used your discounts", true);
                    } else {
                        Cart.setDiscountAmount(discount.getPercentReduction());
                        Cart.setTotalAmountWithDiscount();
                        discount.setUsageNumber(discount.getUsageNumber() - 1);
                        totalAmountLabel.setText(Integer.toString(Cart.getTotalAmount()));
                        discountBtn.setDisable(true);
                        discountField.setDisable(true);
                        hasCode = true;
                        break;
                    }
                }
            }
            if (!hasCode) {
                throw new IOException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter A Valid Discount Code");
            alert.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You don't have such code");
            alert.show();
        }
    }


    public void pay(ActionEvent actionEvent) {
        String numberText = phoneField.getText();
        String addressText = addressField.getText();
        try {
            if (!phoneValidation(numberText))
                throw new ArrayIndexOutOfBoundsException();
            if (numberText.length() == 0 || addressText.length() == 0)
                throw new IOException();
            if (Cart.getTotalAmount() > UserHandler.currentBuyer.getBalance())
                throw new NullPointerException();
            else {
                UserHandler.currentBuyer.addBalance((-1) * Cart.getTotalAmount());
                HashMap<Seller, HashMap<Good, Integer>> soldGoods = new HashMap<>();
                HashMap<Good, Integer> boughtGoods;
                boughtGoods = Cart.getGoods();
                for (Seller seller : ManageInfo.allSellers) {
                    soldGoods.put(seller, new HashMap<>());
                }
                for (Good good : boughtGoods.keySet()) {
                    good.setStock(good.getStock() - boughtGoods.get(good));
                }
                for (Good good : boughtGoods.keySet()) {
                    soldGoods.get(good.getSeller()).put(good, boughtGoods.get(good));
                }
                BuyLog buyLog = new BuyLog(Cart.getTotalAmount(), Cart.getDiscountAmount(), boughtGoods,
                        UserHandler.currentBuyer.getUsername());
                UserHandler.currentBuyer.addMyLogs(buyLog);
                ManageInfo.allBuyLogs.add(buyLog);
                for (Seller seller : soldGoods.keySet()) {
                    SellLog sellLog = new SellLog(countTotalAmount(soldGoods.get(seller)), Cart.getDiscountAmount(),
                            soldGoods.get(seller), UserHandler.currentBuyer.getUsername());
                    seller.addMySellLog(sellLog);
                    ManageInfo.allSellLogs.add(sellLog);
                }
                Cart.resetCart();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Successfully Purchased");
                alert.show();
                MenuHandler.secondCurrentWindow.close();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Complete All Fields");
            alert.show();
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Phone Number");
            alert.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You don't have enough MONEY!!!");
            alert.show();
        }

    }

    private int countTotalAmount(HashMap<Good, Integer> goodIntegerHashMap) {
        int totalAmount = 0;
        for (Good good : goodIntegerHashMap.keySet()) {
            totalAmount += good.getPrice()*goodIntegerHashMap.get(good);
        }
        return (totalAmount * ((100 - Cart.getDiscountAmount()) / 100));
    }

    public void back(ActionEvent actionEvent) {
        MenuHandler.secondCurrentWindow.close();
        MenuHandler.createStageWithScene("Cart");
    }

    private boolean phoneValidation(String phone) {
        String phoneRegex = "^\\+?\\d\\d\\d\\d+$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        return phone.matches(phoneRegex);
    }
}
