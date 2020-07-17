package Server.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User {
    @ElementCollection(targetClass = Sale.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Seller_Sale_map")
    private List<Sale> mySales;
    @ElementCollection(targetClass = Good.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Seller_Good_map")
    private List<Good> myGoods;
    @ElementCollection(targetClass = SellLog.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Seller_SellLog_map")
    private List<SellLog> mySellLog;
    private String sellerCompanyName;
    private int balance;
    public static Seller currentSeller;
    public Seller(){}
    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, String sellerCompanyName  ) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.sellerCompanyName = sellerCompanyName;
        this.setType(UserType.SELLER);
        this.mySellLog = new ArrayList<>();
        this.myGoods = new ArrayList<>();
    }
    public void addMySellLog(SellLog sellLog){
        mySellLog.add(sellLog);
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Sale> getMySales() {
        ArrayList<Sale> sales = new ArrayList();
        sales.addAll(this.mySales);
        return sales;
    }

    public void setMySales(ArrayList<Sale> mySales) {
        this.mySales = mySales;
    }

    public ArrayList<Good> getMyGoods() {
        ArrayList<Good> goods = new ArrayList();
        goods.addAll(this.myGoods);
        return goods;
    }

    public void setMyGoods(ArrayList<Good> myGoods) {
        this.myGoods = myGoods;
    }

    public ArrayList<SellLog> getMySellLog() {
        ArrayList<SellLog> sellLogs = new ArrayList();
        sellLogs.addAll(this.mySellLog);
        return sellLogs;
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
    public void addGood(Good good) {
       myGoods.add(good);
    }
    public ArrayList<String> viewSales() {
        ArrayList<String> myAllSales = new ArrayList<>();
        for (Sale sale : mySales) {
            myAllSales.add(sale.toString());
        }
        return myAllSales;
    }
    public static void registerConfirmation(Seller seller){
        ManageInfo.allSellers.add(seller);
        ManageInfo.allUsers.add(seller);
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

    public void removeProduct(Good good) {
        myGoods.remove(good);
    }
    public static ArrayList<String> getAllBrands(){
        ArrayList<String> brands = new ArrayList<>();
        for (Good good:
             ManageInfo.allGoods) {
            if(!brands.contains(good.getBrand()))
                brands.add(good.getBrand());
        }
        return brands;
    }

}