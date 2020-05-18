package Model;

import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<Sale> mySales;
    private ArrayList<Good> myGoods;
    private ArrayList<SellLog> mySellLog;
    private String sellerCompanyName;
    String workType;
    public static Seller currentSeller;
    public static ArrayList<Seller> getSellers() {
        return sellers;
    }
    public void addMySellLog(SellLog sellLog){
        mySellLog.add(sellLog);
    }
    public static void setSellers(ArrayList<Seller> sellers) {
        Seller.sellers = sellers;
    }

    public static ArrayList<Seller> sellers;

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, String sellerCompanyName, String workType) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.sellerCompanyName = sellerCompanyName;
        this.workType = workType;
        User.users.add(this);
        this.setType(UserType.SELLER);
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

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    public static void createNewSeller() {

    }

    public String viewCompanyInformation(Seller seller) {
        return "Company name: " + seller.getSellerCompanyName() + "\n";
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
