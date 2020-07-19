package Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User {
    @ElementCollection
    private List<Integer> mySales;
    @ElementCollection
    private List<String> myGoods;
    @ElementCollection
    private List<Integer> mySellLog;
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
        this.mySales = new ArrayList<>();
    }
    public void addMySellLog(SellLog sellLog){
        mySellLog.add(sellLog.getId());
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public List<Sale> getMySales() {
        List<Sale> sales = new ArrayList<>();
        for (Integer mySale : mySales) {
            sales.add(Sale.getSaleById(mySale));
        }
        return sales;
    }

    public void setMySales(List<Sale> mySales) {
        List<Integer> salesId = new ArrayList<>();
        for (Sale mySale : mySales) {
            salesId.add(mySale.getId());
        }
        this.mySales = salesId;
    }

    public List<Good> getGoods() {
        List<Good> goods = new ArrayList<>();
        for (String good : this.myGoods) {
            goods.add(Good.getGoodByName(good, ManageInfo.allGoods));
        }
        return goods;
    }

    public void setGoods(List<Good> goods) {
        List<String> goodStr = new ArrayList<>();
        for ( Good good : goods) {
            goodStr.add(good.getName());
        }
        this.myGoods = goodStr;
    }

    public List<SellLog> getMySellLog() {
        List<SellLog> sellLogs = new ArrayList<>();
        for (int id : mySellLog) {
            sellLogs.add(SellLog.getSellLogById(id));
        }
        return sellLogs;
    }

    public void setMySellLog(List<SellLog> mySellLog) {
        List<Integer> mySellLogStr = new ArrayList<>();
        for (SellLog sellLog : mySellLog) {
            mySellLogStr.add(sellLog.getId());
        }
        this.mySellLog = mySellLogStr;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    public static void createNewSeller() {

    }
    public static List<String> getAllBrands(){
        List<String> brands = new ArrayList<>();
        for (Good good:
                ManageInfo.allGoods) {
            if(!brands.contains(good.getBrand()))
                brands.add(good.getBrand());
        }
        return brands;
    }
    public String viewCompanyInformation() {
        return "Company name: " + this.getSellerCompanyName() + "\n";
    }

    public List<String> viewSalesHistory(Seller seller) {
        List<String> mySellHistory = new ArrayList<>();
        for (int log : mySellLog) {
            mySellHistory.add(SellLog.getSellLogById(log).toString());
        }
        return mySellHistory;
    }

    public List<String> viewAllGoods() {
        List<String> myAllGoods = new ArrayList<>();
        for (String good : myGoods) {
            myAllGoods.add(good);
        }
        return myAllGoods;
    }

    public String viewGoodId(Good good) {
        return good.toString();
    }

    public List<String> viewBuyers(Good good) {
        List<Buyer> buyers = good.getBuyers();
        List<String> toStringBuyers = new ArrayList<>();
        for (Buyer buyer : buyers) {
            toStringBuyers.add(buyer.getUsername());
        }
        return toStringBuyers;
    }

    public void editGood(Good good) {

    }
    public void addGood(Good good) {
       myGoods.add(good.getName());
    }
    public List<String> viewSales() {
        List<String> myAllSales = new ArrayList<>();
        for (int sale : mySales) {
            myAllSales.add(Sale.getSaleById(sale).toString());
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

    public String viewBalance() {
        return null;
    }

    public void removeProduct(Good good) {
        myGoods.remove(good);
    }

    public void addSale(Sale sale) {
        this.mySales.add(sale.getId());
    }
}