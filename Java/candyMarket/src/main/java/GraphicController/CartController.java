package GraphicController;

import GraphicView.CustomGoodCart;
import GraphicView.MenuHandler;
import Model.Cart;
import Model.Good;
import Model.UserHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    public static CartController cartController;
    @FXML private ScrollPane scrollPane;
    @FXML private Label totalPriceLabel;

    private static ArrayList<CustomGoodCart> products = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTotalPriceLabel();
        cartController = this;
        VBox allGoods = new VBox();
        HashMap<Good, Integer> goods = Cart.getGoods();
        products = createGoodBoxes(goods);
        allGoods.getChildren().addAll(products);
        scrollPane.setContent(allGoods);
    }

    public void deleteProduct(CustomGoodCart product) {
        VBox allGoods = new VBox();
        products.remove(product);
        allGoods.getChildren().addAll(products);
        scrollPane.setContent(allGoods);
    }

    private ArrayList<CustomGoodCart> createGoodBoxes(HashMap<Good, Integer> goods) {
        ArrayList<CustomGoodCart> customGoodCarts = new ArrayList<>();
        for (Good good : goods.keySet()) {
            customGoodCarts.add(new CustomGoodCart(good));
        }
        return customGoodCarts;
    }

    public void setTotalPriceLabel() {
        Cart.setTotalAmount();
        totalPriceLabel.setText(Integer.toString(Cart.getTotalAmount()));
    }

    public void paying(ActionEvent actionEvent) {
        if (!UserHandler.isLogeIn()) {
            MenuHandler.createStageWithScene("LoginMenu");
        } else {
            ;
        }
    }
}
