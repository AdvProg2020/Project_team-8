package GraphicView;

import GraphicController.CartController;
import Model.Cart;
import Model.Good;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.HashMap;

public class CustomGoodCart extends HBox {
    Label goodName;
    Label numbers;
    Label plus;
    Label minus;
    Label price;
    Label totalPrice;

    public CustomGoodCart(Good good) {
        goodName = new Label();
        numbers = new Label();
        plus = new Label();
        minus = new Label();
        price = new Label();
        totalPrice = new Label();
        goodName.setText(good.getName());
        goodName.setFont(Font.font("Times New Roman", 18));
        numbers.setText(Integer.toString(Cart.getGoods().get(good)));
        numbers.setFont(Font.font("Times New Roman", 18));
        plus.setText("+");
        plus.setFont(Font.font("Times New Roman", 18));
        plus.setCursor(Cursor.HAND);
        minus.setText("-");
        minus.setFont(Font.font("Times New Roman", 18));
        minus.setCursor(Cursor.HAND);
        price.setText(Integer.toString(good.getPrice()));
        price.setFont(Font.font("Times New Roman", 18));
        totalPrice.setFont(Font.font("Times New Roman", 18));
        plus.setOnMouseClicked(e -> {
            if (good.getStock() - Integer.parseInt(numbers.getText()) > 0) {
                numbers.setText(Integer.toString(Integer.parseInt(numbers.getText()) + 1));
                HashMap<Good, Integer> goodIntegerHashMap = Cart.getGoods();
                goodIntegerHashMap.put(good, Cart.getGoods().get(good) + 1);
                totalPrice.setText(Integer.toString(Cart.getGoods().get(good) * good.getPrice()));
                CartController.cartController.setTotalPriceLabel();
                Cart.setGoods(goodIntegerHashMap);
            }
        });
        minus.setOnMouseClicked(e -> {
            numbers.setText(Integer.toString(Integer.parseInt(numbers.getText()) -1));
            HashMap<Good, Integer> goodIntegerHashMap = Cart.getGoods();
            if (Cart.getGoods().get(good) - 1 == 0) {
                goodIntegerHashMap.remove(good);
                CartController.cartController.deleteProduct(this);
            } else {
                goodIntegerHashMap.put(good, Cart.getGoods().get(good) - 1);
                totalPrice.setText(Integer.toString(Cart.getGoods().get(good) * good.getPrice()));
            }
            CartController.cartController.setTotalPriceLabel();
            Cart.setGoods(goodIntegerHashMap);
        });
        totalPrice.setText(Integer.toString(Cart.getGoods().get(good) * good.getPrice()));
        setSpacing(20);
        getChildren().addAll(goodName, numbers, price, plus, minus, totalPrice);
    }
}
