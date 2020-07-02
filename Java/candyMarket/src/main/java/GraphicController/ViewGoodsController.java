package GraphicController;

import GraphicView.CustomButton;
import GraphicView.CustomGoodBox;
import GraphicView.MenuHandler;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Filter;

public class ViewGoodsController implements Initializable {
    public ChoiceBox sortChoiceBox;
    public Button updateListBtn;
    public ScrollPane goodsScrollPane;
    public ScrollPane categoriesScrollPane;
    public CheckBox filterByBrandCheckBox;
    public ScrollPane brandScrollPane;
    public CheckBox filterByCategoryCheckBox;
    public CheckBox filterByAvailableProductsCheckBox;
    public CheckBox filterByOffProductsCheckBox;
    public TextField minimumPriceTxt;
    public TextField maximumPriceTxt;
    public CheckBox filterByPriceCheckBox;
    ArrayList<CustomGoodBox> goodBoxes = new ArrayList<>();
    ArrayList<Good> goods = new ArrayList<>();
    ArrayList<CheckBox> categoryCheckBoxes = new ArrayList<>();
    ArrayList<CheckBox> brandCheckBoxes = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortChoiceBox.getItems().addAll(FilterAndSort.getSortsType());
        sortChoiceBox.setValue("DATE_CREATED");
        setBrandScrollPane();
        setCategoryScrollPane();
        updateCheckBoxes();
        onUpdateListBtnClick(null);
        setGoodsScrollPane();
    }
    private CheckBox getCheckBoxByText(ArrayList<CheckBox> checkBoxes,String text){
        for (CheckBox c:
             checkBoxes) {
            if(c.getText().equals(text))
                return c;
        }
        return null;
    }
    private void updateCheckBoxes(){
        if(FilterAndSort.isAvailableFilterOn)
            filterByAvailableProductsCheckBox.setSelected(true);
        if(FilterAndSort.isBrandFilterOn)
            filterByBrandCheckBox.setSelected(true);
        if(FilterAndSort.isCategoryFilterOn)
            filterByCategoryCheckBox.setSelected(true);
        if(FilterAndSort.isPriceFilterOn)
            filterByPriceCheckBox.setSelected(true);
        if(FilterAndSort.isOffFilterOn)
            filterByOffProductsCheckBox.setSelected(true);
        for (Category c:
             FilterAndSort.categoriesFilter) {
            CheckBox checkBox = getCheckBoxByText(categoryCheckBoxes,c.getName());
            checkBox.setSelected(true);
        }
        for (String s:
                FilterAndSort.brandsFilter) {
            CheckBox checkBox = getCheckBoxByText(brandCheckBoxes,s);
            checkBox.setSelected(true);
        }
        minimumPriceTxt.setText(String.valueOf(FilterAndSort.minPriceFilter));
        maximumPriceTxt.setText(String.valueOf(FilterAndSort.maxPriceFilter));
    }
    private void setBrandScrollPane() {
        ArrayList<String> brands = Seller.getAllBrands();
        brandCheckBoxes = createBrandCheckBoxes(brands);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        for (CheckBox c:
                brandCheckBoxes) {
            vBox.getChildren().add(c);
        }
        brandScrollPane.setContent(vBox);
    }
    private void setCategoryScrollPane(){
        ArrayList<Category> categories = new ArrayList<>();
        categories.addAll(ManageInfo.allCategories);
        categoryCheckBoxes = createCategoryCheckBoxes(categories);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        for (CheckBox c:
             categoryCheckBoxes) {
            vBox.getChildren().add(c);
        }
        categoriesScrollPane.setContent(vBox);
    }
    private void setGoodsScrollPane(){
        VBox allGoods = new VBox();
        goodBoxes = createGoodBoxes(goods);
        for(int i=0;i<goodBoxes.size();i+=2){
            HBox hBox = new HBox();
            hBox.setSpacing(200);
            hBox.getChildren().add(goodBoxes.get(i));
            if(i+1<goodBoxes.size())
                hBox.getChildren().add(goodBoxes.get(i+1));
            allGoods.getChildren().add(hBox);
        }
        goodsScrollPane.setContent(allGoods);
    }
    private ArrayList<CustomGoodBox> createGoodBoxes(ArrayList<Good> goods){
        ArrayList<CustomGoodBox> customGoodBoxes = new ArrayList<>();
        for (Good g:
             goods) {
            CustomGoodBox goodBox;
            if(g.hasImage()) {
                goodBox = new CustomGoodBox(g.getName(), g.getAverageScore(), g.getPrice(), new Image(g.getImage()));
            }

            else goodBox = new CustomGoodBox(g.getName(), g.getAverageScore(), g.getPrice());
            customGoodBoxes.add(goodBox);
        }
        return customGoodBoxes;
    }
    private ArrayList<CheckBox> createCategoryCheckBoxes(ArrayList<Category> categories){
        ArrayList<CheckBox> categoryCheckBoxes = new ArrayList<>();
        for (Category c:
             categories) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(c.getName());
            categoryCheckBoxes.add(checkBox);
        }
        return categoryCheckBoxes;
    }
    private ArrayList<CheckBox> createBrandCheckBoxes(ArrayList<String> brands){
        ArrayList<CheckBox> brandCheckBoxes = new ArrayList<>();
        for (String s:
                brands) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(s);
            brandCheckBoxes.add(checkBox);
        }
        return brandCheckBoxes;
    }
    private void filterByBrand(){
        for (CheckBox c:
                brandCheckBoxes) {
            String brand = c.getText();
            if(c.isSelected() && !FilterAndSort.brandsFilter.contains(brand)) FilterAndSort.brandsFilter.add(brand);
            else if(!c.isSelected()) FilterAndSort.brandsFilter.remove(brand); }
        goods = FilterAndSort.brandFilter(goods);
    }
    private void filterByCategory(){
        for (CheckBox c:
                categoryCheckBoxes) {
            Category category = Category.getCategoryByName(c.getText());
            if(c.isSelected() && !FilterAndSort.categoriesFilter.contains(category)) FilterAndSort.categoriesFilter.add(category);
            else if(!c.isSelected()) FilterAndSort.categoriesFilter.remove(category);
        }
        goods = FilterAndSort.categoryFilter(goods);
    }
    private void filterByAvailableProducts(){
        goods = FilterAndSort.availableProductsFilter(goods);
    }
    private void filterByOffProducts(){
        goods = FilterAndSort.offProductsFilter(goods);
    }
    private void filterByPrice(){
        int minPrice = Integer.parseInt(minimumPriceTxt.getText());
        int maxPrice = Integer.parseInt(maximumPriceTxt.getText());
        FilterAndSort.minPriceFilter = minPrice;
        FilterAndSort.maxPriceFilter =  maxPrice;
        goods = FilterAndSort.priceFilter(goods);
    }
    private void sortGoods(){
        for (FilterAndSort.sortsTypes s:
                FilterAndSort.sortsTypes.values()) {
            if(s.toString().equals(sortChoiceBox.getValue()))
                FilterAndSort.sortsType = s;
        }
        goods = FilterAndSort.sortGoods(goods);
    }
    public void onUpdateListBtnClick(MouseEvent mouseEvent) {
        goods.clear();
        goods.addAll(ManageInfo.allGoods);
        sortGoods();
        if(filterByPriceCheckBox.isSelected()){
            filterByPrice();
            FilterAndSort.isPriceFilterOn = true;
        }else FilterAndSort.isPriceFilterOn = false;
        if(filterByBrandCheckBox.isSelected()){
            filterByBrand();
            FilterAndSort.isBrandFilterOn = true;
        }else FilterAndSort.isBrandFilterOn = false;
        if(filterByCategoryCheckBox.isSelected()){
            filterByCategory();
            FilterAndSort.isCategoryFilterOn = true;
        }else FilterAndSort.isCategoryFilterOn = false;
        if(filterByAvailableProductsCheckBox.isSelected()){
            filterByAvailableProducts();
            FilterAndSort.isAvailableFilterOn = true;
        }else FilterAndSort.isAvailableFilterOn = false;
        if(filterByOffProductsCheckBox.isSelected()){
            filterByOffProducts();
            FilterAndSort.isOffFilterOn = true;
        }else FilterAndSort.isOffFilterOn = false;
        setGoodsScrollPane();
    }
}
