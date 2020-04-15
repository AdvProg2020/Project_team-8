package Model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private ArrayList<Sale> mySales;
    private ArrayList<Good> myGoods;
    private ArrayList<SellLog> mySellLog;
    private String companyName;
    private String factoryName;
    private String workShopName;
    public static ArrayList<Seller> sellers;


    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, UserType type) {
        super(userName, firstName, lastName, email, phoneNumber, passWord, type);
    }

    public static void createNewSeller() {

    }

    public ArrayList<String> viewCompanyInformation(Seller seller) {
        return null;
    }

    public ArrayList<String> viewSalesHistory(Seller seller) {
        return null;
    }

    public ArrayList<String> viewAllGoods() {
        return null;
    }

    public ArrayList<String> viewGoodId(Good good) {
        return null;
    }

    public ArrayList<String> viewBuyers(Good good) {
        return null;
    }

    public void editGood(Good good) {

    }

    public void addGood() {

    }

    public void removeGood(Good good){

    }

    public ArrayList<String> viewSales() {
        return null;
    }

    public ArrayList<String> viewSale(Sale sale) {
        return null;
    }

    public Request sendEditSaleRequest(Sale sale) {
        return null;
    }

    public Request sendAddSaleRequest() {
        return null;
    }

    public String viewBalance() {
        return null;
    }

    
}
