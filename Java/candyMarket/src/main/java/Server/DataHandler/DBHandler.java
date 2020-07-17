package Server.DataHandler;

import Server.Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHandler {
    public static void loadData(){
        List<Manager> managers= DBManager.loadAllData(Manager.class);
        ManageInfo.allManagers.addAll(managers);
        List<Buyer> buyers = DBManager.loadAllData(Buyer.class);
        ManageInfo.allBuyers.addAll(buyers);
        List<Seller> sellers = DBManager.loadAllData(Seller.class);
        ManageInfo.allSellers.addAll(sellers);
        List<Good> goods = DBManager.loadAllData(Good.class);
        ManageInfo.allGoods.addAll(goods);
        List<Category> categories = DBManager.loadAllData(Category.class);
        ManageInfo.allCategories.addAll(categories);
        List<BuyLog> buyLogs = DBManager.loadAllData(BuyLog.class);
        ManageInfo.allBuyLogs.addAll(buyLogs);
        List<SellLog> sellLogs = DBManager.loadAllData(SellLog.class);
        ManageInfo.allSellLogs.addAll(sellLogs);
        List<Comment> comments = DBManager.loadAllData(Comment.class);
        ManageInfo.allComments.addAll(comments);
        List<Discount> discounts = DBManager.loadAllData(Discount.class);
        ManageInfo.allDiscounts.addAll(discounts);
        List<Request> requests = DBManager.loadAllData(Request.class);
        ManageInfo.allRequests.addAll(requests);
        List<Sale> sales = DBManager.loadAllData(Sale.class);
        ManageInfo.allSales.addAll(sales);
        List<Score> scores = DBManager.loadAllData(Score.class);
        ManageInfo.allScores.addAll(scores);
        List<User> users = DBManager.loadAllData(User.class);
        ManageInfo.allUsers.addAll(users);
    }

    public static void main(String[] args) {
        HibernateConfigs.runDataBase();
        //setBuyerDataTest();
        getDataTest();
    }
    public static void getDataTest(){
        loadData();
        for (Buyer m : ManageInfo.allBuyers) {
            if(!m.getMyLogs().isEmpty())System.out.println(m.getMyLogs().get(0).getBuyerName());
            if(!m.getMyLogs().isEmpty())System.out.println(m.getMyLogs().get(0).getGoods().get(0).getName());
        }
        for (Good good : ManageInfo.allGoods) {
            System.out.println(good.getCategory());
            System.out.println(good.getSeller());
        }
    }
    public static void setBuyerDataTest(){
        Buyer buyer = new Buyer("b","a","a","b","c","d");
        DBManager.saveObject(buyer);
        ArrayList<String> at = new ArrayList<>();
        at.add("vazn");
        Category category = new Category("c",at);
        DBManager.saveObject(category);
        Seller seller = new Seller("s","a","a","a","3","w","co");
        DBManager.saveObject(seller);
        Good good = new Good("g","g",2,seller,2,category,"",null,at);
        DBManager.saveObject(good);
        Good good2 = new Good("g2","g",2,seller,2,category,"",null,at);
        DBManager.saveObject(good2);
        Buyer buyer1 = new Buyer("buyer1","a","a","b","c","d");
        HashMap<Good,Integer> goods = new HashMap<>();
        goods.put(good,2);
        BuyLog buyLog = new BuyLog(2,1,goods,"buyer2");
        DBManager.saveObject(buyLog);
        buyer1.addMyLogs(buyLog);
        DBManager.saveObject(buyer1);
        ManageInfo.allBuyers.add(buyer1);
        ManageInfo.allBuyers.add(buyer);
        HibernateConfigs.shutdown();
    }
    public static void setManagerDataTest(){
        Manager manager = new Manager("a","a","a","b","c","d");
        ManageInfo.allManagers.add(manager);
        DBManager.saveObject(manager);
        HibernateConfigs.shutdown();
    }
}
