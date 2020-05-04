package Model;

import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<Sale> mySales;
    private ArrayList<Good> myGoods;
    private ArrayList<SellLog> mySellLog;
    private String companyName;
    private String factoryName;
    private String workShopName;
    public static ArrayList<Seller> sellers;

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, UserType type, String companyName, String factoryName, String workShopName) {
        super(userName, firstName, lastName, email, phoneNumber, passWord, type);
        this.companyName = companyName;
        this.factoryName = factoryName;
        this.workShopName = workShopName;
    }

    public ArrayList<Sale> getMySales() {
        return mySales;
    }

    public void setMySales(ArrayList<Sale> mySales) {
        this.mySales = mySales;
    }

    public ArrayList<Good> getMyGoods() {
        return myGoods;
    }

    public void setMyGoods(ArrayList<Good> myGoods) {
        this.myGoods = myGoods;
    }

    public ArrayList<SellLog> getMySellLog() {
        return mySellLog;
    }

    public void setMySellLog(ArrayList<SellLog> mySellLog) {
        this.mySellLog = mySellLog;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWorkShopName() {
        return workShopName;
    }

    public void setWorkShopName(String workShopName) {
        this.workShopName = workShopName;
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
    public void editSale(Sale sale) {

    }

    public void addSale(Sale sale) {

    }
    public String viewBalance() {
        return null;
    }

    
}
