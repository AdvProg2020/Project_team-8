package Model;

import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<Sale> mySales;
    private ArrayList<Good> myGoods;
    private ArrayList<SellLog> mySellLog;
    private String sellerCompanyName;
    public static Seller currentSeller;
    public static ArrayList<Seller> sellers;
    private boolean confirmedSeller;

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, String sellerCompanyName  ) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.sellerCompanyName = sellerCompanyName;
        this.setType(UserType.SELLER);
        this.confirmedSeller = false;
        sellers.add(this);
    }

    public static ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void addMySellLog(SellLog sellLog){
        mySellLog.add(sellLog);
    }

    public static void setSellers(ArrayList<Seller> sellers) {
        Seller.sellers = sellers;
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

    public String viewCompanyInformation() {
        return "Company name: " + this.getSellerCompanyName() + "\n";
    }

    public ArrayList<String> viewSalesHistory(Seller seller) {
        ArrayList<String> mySellHistory = new ArrayList<>();
        for (SellLog log : mySellLog) {
            mySellHistory.add(log.toString());
        }
        return mySellHistory;
    }

    public ArrayList<String> viewAllGoods() {
        ArrayList<String> myAllGoods = new ArrayList<>();
        for (Good good : myGoods) {
            myAllGoods.add(good.toString());
        }
        return myAllGoods;
    }

    public String viewGoodId(Good good) {
        return good.toString();
    }

    public ArrayList<String> viewBuyers(Good good) {
        ArrayList<Buyer> buyers = good.getBuyers();
        ArrayList<String> toStringBuyers = new ArrayList<>();
        for (Buyer buyer : buyers) {
            toStringBuyers.add(buyer.getUsername());
        }
        return toStringBuyers;
    }

    public void editGood(Good good) {

    }

    public void addGood() {
       //??
    }

    public ArrayList<String> viewSales() {
        ArrayList<String> myAllSales = new ArrayList<>();
        for (Sale sale : mySales) {
            myAllSales.add(sale.toString());
        }
        return myAllSales;
    }

    public String viewSale(Sale sale) {
        return sale.toString();

    }
    public void editSale(Sale sale) {

    }

    public void addSale(Sale sale) {
        //??
    }

    public String viewBalance() {
        return null;
    }

    public void confirmSeller() {
        this.confirmedSeller = true;
    }


}