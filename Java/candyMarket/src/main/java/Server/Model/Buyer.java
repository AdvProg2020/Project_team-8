package Server.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
public class Buyer extends User {
    public ArrayList<Discount> getMyDiscounts() {
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.addAll(myDiscounts);
        return discounts;
    }

    public void setMyDiscounts(ArrayList<Discount> myDiscounts) {
        this.myDiscounts = myDiscounts;
    }
    public Buyer(){

    }
    @ElementCollection(targetClass = Discount.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Buyer_Discount_map")
    private List<Discount> myDiscounts;
    @ElementCollection(targetClass = BuyLog.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Buyer_BuyLog_map")
    private List<BuyLog> myLogs;
    @ElementCollection(targetClass = Good.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "Buyer_Good_map")
    private List<Good> boughtGoods;
    private int balance;
    public static Buyer currentBuyer;
    public Buyer(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord) {
        super(userName, firstName, lastName, email, phoneNumber, passWord);
        this.setType(UserType.BUYER);
        this.balance=0;
        this.myLogs = new ArrayList<>();
        for (BuyLog buyLog : ManageInfo.allBuyLogs) {
            if (buyLog.getBuyerName().equals(userName)) {
                myLogs.add(buyLog);
            }
        }
        myDiscounts = new ArrayList<>();
        ManageInfo.allBuyers.add(this);
        ManageInfo.allUsers.add(this);
        getDiscountCodeRandom();
    }
    private void getDiscountCodeRandom(){
        Random rand = new Random();
        int size = ManageInfo.allDiscounts.size();
        if(size==0) return;
        int randNumber;
        randNumber = rand.nextInt(100);
        if(randNumber % 1 == 0) {
            randNumber = rand.nextInt(size);
            Discount discount = ManageInfo.allDiscounts.get(randNumber);
            myDiscounts.add(discount);
        }
    }
    public Cart getCart() {
        return cart;
    }
    public BuyLog getBuyLogById(int id){
        for (BuyLog b:
             myLogs) {
            if(b.getId()==id)
                return b;
        }
        return null;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void addComment(Good good, String title, String content){
        Comment comment = new Comment(UserHandler.getCurrentUser(),good,content,title);
        //Request request = new Request(comment.toString(), Request.requestType.ADD_COMMENT);
        //ManageInfo.allRequests.add(request);
    }

    public static void register(String userName,String firstName,String lastName,String email,String phone,String pass){
        new Buyer(userName, firstName, lastName, email, phone, pass);
    }

    public ArrayList<String> viewCart(Cart cart) {
        return null;
    }

    public ArrayList<String> viewGoodsInCart(Cart cart) {
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

    public ArrayList<String> showOrders() {
        return null;
    }

    public void rate(Good good, int score) {
        ArrayList<Score> scores = good.getScores();
        scores.add(new Score(this, score, good));
        good.setScores(scores);
    }

    public String viewBuyerBalance() {
        return null;
    }

    public ArrayList<String> viewAllDiscountsCode() {
        ArrayList<String> disCode = new ArrayList<>();
        for (Discount discount : myDiscounts) {
            disCode.add(discount.toString());
        }
        return disCode;
    }


    public ArrayList<BuyLog> getMyLogs() {
        ArrayList<BuyLog> arrayList= new ArrayList<>();
        arrayList.addAll(myLogs);
        return arrayList;
    }

    public void setMyLogs(ArrayList<BuyLog> myLogs) {
        this.myLogs = myLogs;
    }

    public void addMyLogs(BuyLog buyLog) {
        this.myLogs.add(buyLog);
    }

    public ArrayList<Good> getBoughtGoods() {
        ArrayList<Good> goods = new ArrayList<>();
        goods.addAll(boughtGoods);
        return goods;
    }

    public void setBoughtGoods(ArrayList<Good> boughtGoods) {
        this.boughtGoods = boughtGoods;
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
