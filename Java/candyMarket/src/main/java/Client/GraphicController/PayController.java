package Client.GraphicController;

import Client.Controller;
import Client.DataHandler.MessageHandler;
import Client.DataHandler.WalletExceptions;
import Client.GraphicView.MenuHandler;
import Client.GraphicView.PrimaryMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Client.Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class PayController implements Initializable {
    public static PayController payController;
    @FXML private TextField phoneField;
    @FXML private TextArea addressField;
    @FXML private TextField discountField;

    @FXML private Button discountBtn;

    @FXML private Label totalAmountLabel;

    @FXML private RadioButton payBankRadioBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        payController = this;
        Cart.setTotalAmount();
        totalAmountLabel.setText(Integer.toString(Cart.getTotalAmount()));
    }


    public void setDiscountCode(ActionEvent actionEvent) {
        List<Discount> discounts = UserHandler.currentBuyer.getMyDiscounts();
        try {
            String codeText = discountField.getText();
            boolean hasCode = false;
            for (Discount discount : discounts) {
                if (discount.getCode().equals(codeText)) {
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
                    Controller.saveOrUpdateObject(discount);
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
            if (Cart.getTotalAmount() > UserHandler.currentBuyer.getBalance() && !payBankRadioBtn.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You don't have enough MONEY!!!");
                alert.show();
            }
            else {
                if (!payBankRadioBtn.isSelected()) {
                    UserHandler.currentBuyer.addBalance((-1) * Cart.getTotalAmount());
                    finishBuying();
                }
                else {
                    MenuHandler.createStageWithScene("PayWithBankAccount");
                }

            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Complete All Fields");
            alert.show();
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Phone Number");
            alert.show();
        }

    }

    public void finishBuying() {
        String address = addressField.getText();
        String phoneNumber = phoneField.getText();
        if (Cart.getTotalAmount() > 10000) {
            Random rand = new Random();
            int randNumber = rand.nextInt(ManageInfo.allDiscounts.size());
            Discount discount = ManageInfo.allDiscounts.get(randNumber);
            boolean added = false;
            for (Discount myDiscount : UserHandler.currentBuyer.getMyDiscounts()) {
                if (myDiscount == discount) {
                    added = true;
                    myDiscount.setUsageNumber(myDiscount.getUsageNumber() + 1);
                }
            }
            if (!added)
                UserHandler.currentBuyer.addDiscount(discount);
        }
        HashMap<Seller, HashMap<Good, Integer>> soldGoods = new HashMap<>();
        HashMap<Good, Integer> boughtGoods;
        boughtGoods = Cart.getGoods();
        for (Seller seller : ManageInfo.allSellers) {
            soldGoods.put(seller, new HashMap<>());
        }
        for (Good good : boughtGoods.keySet()) {
            good.setStock(good.getStock() - boughtGoods.get(good));
            Controller.saveOrUpdateObject(good);
        }
        for (Good good : boughtGoods.keySet()) {
            soldGoods.get(good.getSeller()).put(good, boughtGoods.get(good));
        }
        BuyLog buyLog = new BuyLog(Cart.getTotalAmount(), Cart.getDiscountAmount(), boughtGoods,
                UserHandler.currentBuyer.getUsername());
        buyLog.setAddress(address);
        buyLog.setPhoneNumber(phoneNumber);
        UserHandler.currentBuyer.addMyLogs(buyLog);
        Controller.saveOrUpdateObject(UserHandler.currentBuyer);
        for (Seller seller : soldGoods.keySet()) {
            SellLog sellLog = new SellLog(countTotalAmount(soldGoods.get(seller)), Cart.getDiscountAmount(),
                    soldGoods.get(seller), UserHandler.currentBuyer.getUsername());
            seller.addMySellLog(sellLog);
            int sellerMoneyAdded = 0;
            for (Good good : sellLog.getGoods().keySet()) {
                sellerMoneyAdded += (int)(good.getPrice()*((double)(100 - (double)Cart.getDiscountAmount())/100));
            }
            sellerMoneyAdded = (int) (sellerMoneyAdded * ((double)(100 - (double)ManageInfo.allManagers.get(0).getWage()) / 100));
            seller.setBalance(seller.getBalance() + sellerMoneyAdded);
            Controller.saveOrUpdateObject(seller);
        }
        Cart.resetCart();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Successfully Purchased");
        alert.show();
        MenuHandler.secondCurrentWindow.close();
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
