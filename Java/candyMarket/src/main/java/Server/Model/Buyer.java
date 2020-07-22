package Server.Model;

import Client.DataHandler.DataAccessor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Buyer extends User {
    public List<Discount> getMyDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        for (String discount : myDiscounts) {
            discounts.add(Discount.getDiscountByCode(discount));
        }
        return discounts;
    }
    public void setMyDiscounts(List<Discount> myDiscounts) {
        List<String> discounts = new ArrayList<>();
        for (Discount discount : myDiscounts) {
            discounts.add(discount.getCode());
        }
        this.myDiscounts = discounts;
    }
    @ElementCollection
    private List<String> myDiscounts = new ArrayList<>();
    @ElementCollection
    private List<Integer> myLogs = new ArrayList<>();
    @ElementCollection
    private List<String> boughtGoods = new ArrayList<>();
    private int balance;
    public static Buyer currentBuyer;
    public Buyer(){}
    public Buyer(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.BUYER);
        this.balance=0;
        this.myLogs = new ArrayList<>();
        this.myDiscounts = new ArrayList<>();
        this.boughtGoods = new ArrayList<>();
        getDiscountCodeRandom();
    }

    private void getDiscountCodeRandom(){
        Random rand = new Random();
        int size = ManageInfo.allDiscounts.size();
        if(size==0) return;
        int randNumber;
        randNumber = rand.nextInt(100);
        if(randNumber % 3 == 0) {
            randNumber = rand.nextInt(size);
            Discount discount = ManageInfo.allDiscounts.get(randNumber);
            myDiscounts.add(discount.getCode());
        }
    }

    public void addDiscount(Discount discount) {
        myDiscounts.add(discount.getCode());
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void addComment(Good good, String title, String brand){
        Comment comment = new Comment(UserHandler.getCurrentUser(),good,brand,null);
        //Request request = new Request(comment.toString(), Request.requestType.ADD_COMMENT);
        //ManageInfo.allRequests.add(request);
    }

    public static void register(String userName,String firstName,String lastName,String email,String phone,String pass){
        new Buyer(userName, firstName, lastName, email, phone, pass);
    }

    public List<String> viewCart(Cart cart) {
        return null;
    }

    public List<String> viewGoodsInCart(Cart cart) {
        return null;
    }

    public void increaseGoodAmountInCart(Cart cart, Good good) {

    }

    public void decreaseGoodAmountInCart(Cart cart, Good good) {

    }

    public void purchase(CartSituation cartSituation) {

    }

    public void receiveInformation() {

    }

    public void applyDiscountCode(Cart cart, Discount discount) {

    }

    public void payment(Cart cart) {

    }

    public List<String> showOrders() {
        return null;
    }

    public void rate(Good good, int score) {
        List<Score> scores = good.getScores();
        scores.add(new Score(this, score, good));
        good.setScores(scores);
    }

    public void removeDiscount(Discount discount) {
        myDiscounts.remove(discount);
    }

    public String viewBuyerBalance() {
        return null;
    }

    public ArrayList<Discount> viewAllDiscountsCode() {
        ArrayList<Discount> disCode = new ArrayList<>();
        for (String discount : myDiscounts) {
            disCode.add(Discount.getDiscountByCode(discount));
        }
        return disCode;
    }


    public List<BuyLog> getMyLogs() {
        List<BuyLog> buyLogs = new ArrayList<>();
        for (Integer myLog : myLogs) {
            buyLogs.add(BuyLog.getBuyLogById(myLog));
        }
        return buyLogs;
    }

    public void setMyLogs(List<BuyLog> myLogs) {
        List<Integer> buyLogs = new ArrayList<>();
        for (BuyLog log : myLogs) {
            buyLogs.add(log.getId());
        }
        this.myLogs = buyLogs;
    }

    public void addMyLogs(BuyLog buyLog) {
        this.myLogs.add(buyLog.getId());
    }

    public List<Good> getBoughtGoods()
    {
        List<Good> goods = new ArrayList<>();
        for (String good : boughtGoods) {
            goods.add(Good.getGoodByName(good));
        }
        return goods;
    }

    public void setBoughtGoods(List<Good> boughtGoods) {
        List<String> goods = new ArrayList<>();
        for (Good good : boughtGoods) {
            goods.add(good.getName());
        }
        this.boughtGoods = goods;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return username;
    }
}
